package xiaoxin.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import xiaoxin.spzx.model.dto.product.CategoryBrandDto;
import xiaoxin.spzx.model.entity.product.Brand;
import xiaoxin.spzx.model.entity.product.CategoryBrand;

import java.util.List;

/**
 * ClassName: CategoryBrandService
 * Package: xiaoxin.spzx.manager.service
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 10:51
 * @Version 1.0
 */
public interface CategoryBrandService {
    PageInfo<CategoryBrand> findByPage(int page, int limit, CategoryBrandDto categoryBrandDto);

    void save(CategoryBrand categoryBrand);

    void updateById(CategoryBrand categoryBrand);

    void deleteById(Long id);

    List<Brand> findBrandByCategoryId(Long categoryId);
}
