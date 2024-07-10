package xiaoxin.spzx.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import xiaoxin.spzx.model.entity.product.Brand;

import java.util.List;

/**
 * ClassName: BrandMapper
 * Package: xiaoxin.spzx.product.mapper
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 20:48
 * @Version 1.0
 */
@Mapper
public interface BrandMapper {
    List<Brand> findAll();

}
