package xiaoxin.spzx.product.service;

import com.github.pagehelper.PageInfo;
import xiaoxin.spzx.model.dto.h5.ProductSkuDto;
import xiaoxin.spzx.model.entity.product.ProductSku;
import xiaoxin.spzx.model.vo.h5.ProductItemVo;

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


    PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto);

    ProductItemVo item(Long skuId);

    ProductSku getBySkuId(Long skuId);
}
