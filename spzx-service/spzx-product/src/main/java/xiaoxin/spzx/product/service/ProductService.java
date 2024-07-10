package xiaoxin.spzx.product.service;

import xiaoxin.spzx.model.entity.product.ProductSku;

import java.util.List;

/**
 * ClassName: ProductService
 * Package: xiaoxin.spzx.product.service
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 17:13
 * @Version 1.0
 */
public interface ProductService {
    List<ProductSku> findProductzSkuBySale();


}
