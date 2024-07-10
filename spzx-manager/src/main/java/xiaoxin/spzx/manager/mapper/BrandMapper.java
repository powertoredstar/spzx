package xiaoxin.spzx.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import xiaoxin.spzx.model.entity.product.Brand;

import java.util.List;


/**
 * ClassName: BrandMapper
 * Package: xiaoxin.spzx.manager.mapper
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 10:21
 * @Version 1.0
 */
@Mapper
public interface BrandMapper {


    List<Brand> findByPage();

    void save(Brand brand);

    void updateById(Brand brand);

    void deleteById(Integer id);

    List<Brand> findAll();

}
