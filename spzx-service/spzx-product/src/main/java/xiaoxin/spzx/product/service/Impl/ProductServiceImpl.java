package xiaoxin.spzx.product.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xiaoxin.spzx.model.entity.product.ProductSku;
import xiaoxin.spzx.product.mapper.ProductMapper;
import xiaoxin.spzx.product.service.ProductService;

import java.util.List;

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
    @Override
    public List<ProductSku> findProductzSkuBySale() {
        return productMapper.findProductzSkuBySale();
    }
}
