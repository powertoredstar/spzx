package xiaoxin.spzx.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import xiaoxin.spzx.model.entity.product.ProductDetails;
import xiaoxin.spzx.model.entity.product.ProductSku;

import java.util.List;

/**
 * ClassName: ProductDetailsMapper
 * Package: xiaoxin.spzx.product.mapper
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 21:24
 * @Version 1.0
 */
@Mapper
public interface ProductDetailsMapper {

    ProductDetails getByProductId(Long productId);
}
