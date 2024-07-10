package xiaoxin.spzx.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import xiaoxin.spzx.model.entity.product.Category;

import java.util.List;

/**
 * ClassName: CategoryMapper
 * Package: xiaoxin.spzx.manager.mapper
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 9:12
 * @Version 1.0
 */
@Mapper
public interface CategoryMapper {
    List<Category> selectByParentId(Long parentId);

    int countByParentId(Long id);

    List<Category> selectAll();

    void batchInser(List cachedDataList);
}
