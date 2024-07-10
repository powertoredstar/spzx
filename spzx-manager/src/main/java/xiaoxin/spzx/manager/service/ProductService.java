package xiaoxin.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import xiaoxin.spzx.model.dto.product.ProductDto;
import xiaoxin.spzx.model.entity.product.Product;

/**
 * ClassName: ProductService
 * Package: xiaoxin.spzx.manager.service
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 11:43
 * @Version 1.0
 */
public interface ProductService {
    PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto);

    void save(Product product);

    Product getById(Long id);

    void updateById(Product product);

    void deleteById(Long id);

    void updateAuditStatus(Long id, Integer auditStatus);

    void updateStatus(Long id, Integer status);
}
