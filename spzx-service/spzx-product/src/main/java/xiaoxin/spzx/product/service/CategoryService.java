package xiaoxin.spzx.product.service;

import xiaoxin.spzx.model.entity.product.Category;

import java.util.List;

/**
 * ClassName: CategoryService
 * Package: xiaoxin.spzx.product.service
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 17:03
 * @Version 1.0
 */
public interface CategoryService {
    List<Category> findOneCategory();

    List<Category> findCategoryTree();

}
