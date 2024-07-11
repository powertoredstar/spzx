package xiaoxin.spzx.cart.service.Impl;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import xiaoxin.spzx.cart.service.CartService;
import xiaoxin.spzx.feign.product.ProductFeignClient;
import xiaoxin.spzx.model.entity.h5.CartInfo;
import xiaoxin.spzx.model.entity.product.ProductSku;
import xiaoxin.spzx.utils.AuthContextUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: CartServiceImpl
 * Package: xiaoxin.spzx.cart.service.Impl
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 9:17
 * @Version 1.0
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private ProductFeignClient productFeignClient;

    private String getCartKey(Long userId) {
        //定义key user:cart:userId
        return "user:cart:" + userId;
    }

    @Override
    public void addToCart(Long skuId, Integer skuNum) {
        //获取当前登录用户的id
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);
        //获取缓存对象
        Object cartInfoObj = redisTemplate.opsForHash().get(cartKey, String.valueOf(skuId));
        CartInfo cartInfo = null;
        if (cartInfoObj != null) {
            //如果购物车中有该商品,则商品数量相加
            cartInfo = JSON.parseObject(cartInfoObj.toString(), CartInfo.class);
            cartInfo.setSkuNum(skuNum);
            cartInfo.setUpdateTime(new Date());
            cartInfo.setIsChecked(1);
        } else {
            //当购物车中没有该商品的时候,则直接添加到购物车
            cartInfo = new CartInfo();
            //购物车数据是从商品详情得到的(nacos+openfeign调用远程接口)
            ProductSku productSku = productFeignClient.getBySkuId(userId);
            cartInfo.setCartPrice(productSku.getSalePrice());
            cartInfo.setSkuNum(skuNum);
            cartInfo.setSkuId(skuId);
            cartInfo.setUserId(userId);
            cartInfo.setImgUrl(productSku.getThumbImg());
            cartInfo.setSkuName(productSku.getSkuName());
            cartInfo.setIsChecked(1);
            cartInfo.setCreateTime(new Date());
            cartInfo.setUpdateTime(new Date());
        }

        //将商品数据存储到购物车中
        redisTemplate.opsForHash().put(cartKey, String.valueOf(skuId), JSON.toJSONString(cartInfo));
    }

    @Override
    public List<CartInfo> getCartList() {
        //获取当前登录的用户信息
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);
        //获取数据
        List<Object> cartInfoList = redisTemplate.opsForHash().values(cartKey);
        if (cartInfoList != null) {
            List<CartInfo> infoList = cartInfoList.stream()
                    .map(cartInfoJson -> JSON.parseObject(cartInfoJson.toString(), CartInfo.class))
                    .sorted((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()))
                    .collect(Collectors.toList());
            return infoList;
        }
        return new ArrayList<>();
    }

    @Override
    public void deleteCart(Long skuId) {
        //获取当前登录的用户数据
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);
        //删除缓存对象
        redisTemplate.opsForHash().delete(cartKey, String.valueOf(skuId));
    }

    @Override
    public void checkCart(Long skuId, Integer isChecked) {
        //获取当前登录的用户数据
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);

        Boolean hasKey = redisTemplate.opsForHash().hasKey(cartKey, String.valueOf(skuId));
        if (hasKey) {
            String cartInfoJson = redisTemplate.opsForHash().get(cartKey, String.valueOf(skuId)).toString();
            CartInfo cartInfo = JSON.parseObject(cartInfoJson, CartInfo.class);
            cartInfo.setIsChecked(isChecked);
            redisTemplate.opsForHash().put(cartKey, String.valueOf(skuId), JSON.toJSONString(cartInfo));
        }
    }

    @Override
    public void allCheckCart(Integer isChecked) {
        //获取当前登录的用户数据
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);
        //获取所有的购物项数据
        List<Object> objectList = redisTemplate.opsForHash().values(cartKey);
        if (!CollectionUtils.isEmpty(objectList)) {
            objectList.stream().
                    map(cartInfoJson -> {
                        CartInfo cartInfo = JSON.parseObject(cartInfoJson.toString(), CartInfo.class);
                        cartInfo.setIsChecked(isChecked);
                        return cartInfo;
                    })
                    .forEach(cartInfo -> redisTemplate.opsForHash().put(cartKey, String.valueOf(cartInfo.getSkuId()), JSON.toJSONString(cartInfo)));
        }
    }

    @Override
    public void clearCart() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);
        redisTemplate.delete(cartKey);
    }

    @Override
    public List<CartInfo> getAllChecked() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);
        //获取所有的购物数据项
        List<Object> objectList = redisTemplate.opsForHash().values(cartKey);
        if (!CollectionUtils.isEmpty(objectList)) {
            List<CartInfo> cartInfoList = objectList.stream()
                    .map(cartInfoJson -> JSON.parseObject(cartInfoJson.toString(),CartInfo.class))
                    .filter(cartInfo -> cartInfo.getIsChecked() == 1)
                    .collect(Collectors.toList());
            return cartInfoList;
        }
        return new ArrayList<>();
    }

    @Override
    public void deleteChecked() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);
        //拿到购物车中的东西
        List<Object> objectList = redisTemplate.opsForHash().values(cartKey);
        //删除选中的购物项数据
        if (!CollectionUtils.isEmpty(objectList)) {
            objectList.stream()
                    .map(cartInfoJson -> JSON.parseObject(cartInfoJson.toString(),CartInfo.class))
                    .filter(cartInfo -> cartInfo.getIsChecked() == 1)
                    .forEach(cartInfo -> redisTemplate.opsForHash().delete(cartKey, String.valueOf(cartInfo.getSkuId())));
        }

    }
}