package xiaoxin.spzx.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import xiaoxin.spzx.model.entity.base.ProductUnit;

import java.util.List;

/**
 * ClassName: ProductUnitMapper
 * Package: xiaoxin.spzx.manager.mapper
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 13:38
 * @Version 1.0
 */
@Mapper
public interface ProductUnitMapper {
    List<ProductUnit> findAll();

}
