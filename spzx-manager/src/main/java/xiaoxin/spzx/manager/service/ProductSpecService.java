package xiaoxin.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import xiaoxin.spzx.model.entity.product.ProductSpec;

import java.util.List;

/**
 * ClassName: ProductSpecService
 * Package: xiaoxin.spzx.manager.service
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 11:21
 * @Version 1.0
 */
public interface ProductSpecService {
    PageInfo<ProductSpec> findByPage(Integer page, Integer limit);

    void save(ProductSpec productSpec);

    void updateById(ProductSpec productSpec);

    void deleteById(Long id);

    List<ProductSpec> findAll();

}
