package xiaoxin.spzx.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import xiaoxin.spzx.model.dto.product.CategoryBrandDto;
import xiaoxin.spzx.model.entity.product.Brand;
import xiaoxin.spzx.model.entity.product.CategoryBrand;

import java.util.List;

/**
 * ClassName: CategoryBrandMapper
 * Package: xiaoxin.spzx.manager.mapper
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 10:56
 * @Version 1.0
 */
@Mapper
public interface CategoryBrandMapper {
    List<CategoryBrand> findByPage(CategoryBrandDto categoryBrandDto);

    void save(CategoryBrand categoryBrand);

    void updateById(CategoryBrand categoryBrand);

    void deleteById(Long id);

    List<Brand> findBrandByCategoryId(Long categoryId);
}
