package xiaoxin.spzx.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import xiaoxin.spzx.model.entity.product.Category;

import java.util.List;

/**
 * ClassName: CategoryMapper
 * Package: xiaoxin.spzx.product.mappper
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 17:04
 * @Version 1.0
 */
@Mapper
public interface CategoryMapper {
    List<Category> findOneCategory();

    List<Category> findAll();

}
