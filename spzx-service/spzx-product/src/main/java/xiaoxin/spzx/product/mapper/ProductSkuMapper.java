package xiaoxin.spzx.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import xiaoxin.spzx.model.entity.product.ProductSku;

import java.util.List;

/**
 * ClassName: ProductSkuMapper
 * Package: xiaoxin.spzx.product.mappper
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 17:15
 * @Version 1.0
 */
@Mapper
public interface ProductSkuMapper {
    ProductSku getById(Long skuId);

    List<ProductSku> findByProductId(Long productId);
}
