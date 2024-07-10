package xiaoxin.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import xiaoxin.spzx.model.entity.product.Brand;

import java.util.List;

/**
 * ClassName: BrandService
 * Package: xiaoxin.spzx.manager.service
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 10:20
 * @Version 1.0
 */
public interface BrandService {
    PageInfo<Brand> findByPage(Integer page, Integer limit);

    void save(Brand brand);

    void updateById(Brand brand);

    void deleteById(Integer id);

    List<Brand> findAll();

}
