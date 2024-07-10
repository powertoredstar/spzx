package xiaoxin.spzx.product.service.Impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xiaoxin.spzx.model.dto.h5.ProductSkuDto;
import xiaoxin.spzx.model.entity.product.Product;
import xiaoxin.spzx.model.entity.product.ProductDetails;
import xiaoxin.spzx.model.entity.product.ProductSku;
import xiaoxin.spzx.model.vo.h5.ProductItemVo;
import xiaoxin.spzx.product.mapper.ProductDetailsMapper;
import xiaoxin.spzx.product.mapper.ProductMapper;
import xiaoxin.spzx.product.mapper.ProductSkuMapper;
import xiaoxin.spzx.product.service.ProductService;

import java.util.*;

/**
 * ClassName: ProductServiceImpl
 * Package: xiaoxin.spzx.product.service.Impl
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 17:13
 * @Version 1.0
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductDetailsMapper productDetailsMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Override
    public List<ProductSku> findProductzSkuBySale() {
        return productMapper.findProductzSkuBySale();
    }

    @Override
    public PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto) {
        PageHelper.startPage(page,limit);
        List<ProductSku> productSkuList = productMapper.findByPage(productSkuDto);
        return new PageInfo<>(productSkuList);
    }

    @Override
    public ProductItemVo item(Long skuId) {
        //当前sku信息
       ProductSku productSku =  productSkuMapper.getById(skuId);
        //当前商品信息
        Product product = productMapper.getById(productSku.getProductId());
        //同一个商品下面的sku信息列表
        List<ProductSku> productSkuList = productSkuMapper.findByProductId(productSku.getProductId());
        //建立sku规格与skuId对应关系
        Map<String, Object> skuSpecValueMap = new HashMap<>();
        productSkuList.forEach(item -> {
            skuSpecValueMap.put(item.getSkuSpec(),item.getId());
        });
        //商品详情信息
        ProductDetails productDetails = productDetailsMapper.getByProductId(productSku.getProductId());
        ProductItemVo productItemVo = new ProductItemVo();
        productItemVo.setProductSku(productSku);
        productItemVo.setProduct(product);
        productItemVo.setDetailsImageUrlList(Arrays.asList(productDetails.getImageUrls().split(",")));
        productItemVo.setSkuSpecValueMap(skuSpecValueMap);
        productItemVo.setSpecValueList(JSON.parseArray(product.getSpecValue()));
        return productItemVo;
    }
}
