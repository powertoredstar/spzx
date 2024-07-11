package xiaoxin.spzx.order.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import xiaoxin.exception.XiaoxinException;
import xiaoxin.spzx.feign.cart.CartFeignClient;
import xiaoxin.spzx.feign.product.ProductFeignClient;
import xiaoxin.spzx.feign.user.UserFeignClient;
import xiaoxin.spzx.model.dto.h5.OrderInfoDto;
import xiaoxin.spzx.model.entity.h5.CartInfo;
import xiaoxin.spzx.model.entity.order.OrderInfo;
import xiaoxin.spzx.model.entity.order.OrderItem;
import xiaoxin.spzx.model.entity.order.OrderLog;
import xiaoxin.spzx.model.entity.product.ProductSku;
import xiaoxin.spzx.model.entity.user.UserAddress;
import xiaoxin.spzx.model.entity.user.UserInfo;
import xiaoxin.spzx.model.vo.common.ResultCodeEnum;
import xiaoxin.spzx.model.vo.h5.TradeVo;
import xiaoxin.spzx.order.mapper.OrderInfoMapper;
import xiaoxin.spzx.order.mapper.OrderItemMapper;
import xiaoxin.spzx.order.mapper.OrderLogMapper;
import xiaoxin.spzx.order.service.OrderInfoService;
import xiaoxin.spzx.utils.AuthContextUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClassName: OrderInfoServiceImpl
 * Package: xiaoxin.spzx.order.service.Impl
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 10:30
 * @Version 1.0
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private CartFeignClient cartFeignClient;
    @Autowired
    private ProductFeignClient productFeignClient;
    @Autowired
    private UserFeignClient userFeignClient;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private OrderLogMapper orderLogMapper;
    @Override
    public TradeVo getTrade() {
        //获取当前登录的用户id
        //获取选中的购物项列表数据
        List<CartInfo> cartInfoList = cartFeignClient.getAllChecked();
        List<OrderItem> orderItemList = new ArrayList<>();
        for (CartInfo cartInfo : cartInfoList) {
            //将购物项数据转换成订单明细数据
            OrderItem orderItem = new OrderItem();
            orderItem.setSkuId(cartInfo.getSkuId());
            orderItem.setSkuName(cartInfo.getSkuName());
            orderItem.setSkuNum(cartInfo.getSkuNum());
            orderItem.setSkuPrice(cartInfo.getCartPrice());
            orderItem.setThumbImg(cartInfo.getImgUrl());
            orderItemList.add(orderItem);
        }
        //计算总金额
        BigDecimal totalAmount = new BigDecimal(0);
        for (OrderItem orderItem : orderItemList) {
            totalAmount = totalAmount.add(orderItem.getSkuPrice()
                    .multiply(new BigDecimal(orderItem.getSkuNum())));
        }
        TradeVo tradeVo = new TradeVo();
        tradeVo.setTotalAmount(totalAmount);
        tradeVo.setOrderItemList(orderItemList);
        return tradeVo;
    }
    @Transactional
    @Override
    public Long submitOrder(OrderInfoDto orderInfoDto) {
        //数据校验
        List<OrderItem> orderItemList = orderInfoDto.getOrderItemList();
        if(CollectionUtils.isEmpty(orderItemList)){
            throw new XiaoxinException(ResultCodeEnum.DATA_ERROR);
        }
            //检查是否有该商品的存在
        for(OrderItem orderItem : orderItemList){
            ProductSku productSku = productFeignClient.getBySkuId(orderItem.getSkuId());
            if(productSku == null){
                throw new XiaoxinException(ResultCodeEnum.DATA_ERROR);
            }
            //校验库存
            if(orderItem.getSkuNum().intValue() > productSku.getStockNum().intValue()){
                throw new XiaoxinException(ResultCodeEnum.STOCK_LESS);
            }
        }
        //构建订单数据,保存订单
        UserInfo userInfo = AuthContextUtil.getUserInfo();
        OrderInfo orderInfo = new OrderInfo();
        //订单编号
        orderInfo.setOrderNo(String.valueOf(System.currentTimeMillis()));
        //用户id
        orderInfo.setUserId(userInfo.getId());
        //用户昵称
        orderInfo.setNickName(userInfo.getNickName());
        //用户收货地址
        UserAddress userAddress = userFeignClient.getUserAddress(orderInfoDto.getUserAddressId());
        orderInfo.setReceiverName(userAddress.getName());
        orderInfo.setReceiverPhone(userAddress.getPhone());
        orderInfo.setReceiverTagName(userAddress.getTagName());
        orderInfo.setReceiverAddress(userAddress.getFullAddress());
        orderInfo.setReceiverProvince(userAddress.getProvinceCode());
        orderInfo.setReceiverCity(userAddress.getCityCode());
        orderInfo.setReceiverDistrict(userAddress.getDistrictCode());
        //订单金额
        BigDecimal totalAmount = new BigDecimal(0);
        for (OrderItem orderItem : orderItemList) {
            totalAmount = totalAmount.add(orderItem.getSkuPrice().multiply(new BigDecimal(orderItem.getSkuNum())));
        }
        orderInfo.setTotalAmount(totalAmount);
        orderInfo.setCouponAmount(new BigDecimal(0));
        orderInfo.setOriginalTotalAmount(totalAmount);
        orderInfo.setFeightFee(orderInfo.getFeightFee());
        orderInfo.setPayType(2);
        orderInfo.setOrderStatus(0);
        orderInfoMapper.save(orderInfo);
        //保存订单明细
        for (OrderItem orderItem : orderItemList) {
            orderItem.setOrderId(orderInfo.getId());
            orderItemMapper.save(orderItem);
        }
        //记录日志
        OrderLog orderLog = new OrderLog();
        orderLog.setOrderId(orderInfo.getId());
        orderLog.setProcessStatus(0);
        orderLog.setNote("提交订单");
        orderLogMapper.save(orderLog);

        //远程调用service-cart微服务接口清空购物车数据
        cartFeignClient.deleteChecked();

        return orderInfo.getId();
    }

    @Override
    public OrderInfo getOrderInfo(Long orderId) {
        return orderInfoMapper.getById(orderId);
    }

    @Override
    public TradeVo buy(Long skuId) {
        //查询商品
        ProductSku productSku = productFeignClient.getBySkuId(skuId);
        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = new OrderItem();
        orderItem.setSkuId(skuId);
        orderItem.setSkuName(productSku.getSkuName());
        orderItem.setSkuNum(1);
        orderItem.setSkuPrice(productSku.getSalePrice());
        orderItem.setThumbImg(productSku.getThumbImg());
        orderItemList.add(orderItem);
        //计算金额
        BigDecimal totalAmount = productSku.getSalePrice();
        TradeVo tradeVo = new TradeVo();
        tradeVo.setTotalAmount(totalAmount);
        tradeVo.setOrderItemList(orderItemList);
        //返回
        return tradeVo;
    }

    @Override
    public PageInfo<OrderInfo> findUserPage(Integer page, Integer limit, Integer orderStatus) {
        PageHelper.startPage(page, limit);
        Long userId = AuthContextUtil.getUserInfo().getId();
        List<OrderInfo> orderInfoList = orderInfoMapper.findUserPage(userId,orderStatus);
        orderInfoList.forEach(orderInfo -> {
            List<OrderItem> orderItems = orderItemMapper.findByOrderId(orderInfo.getId());
            orderInfo.setOrderItemList(orderItems);
        });
        return new PageInfo<>(orderInfoList);
    }
}
