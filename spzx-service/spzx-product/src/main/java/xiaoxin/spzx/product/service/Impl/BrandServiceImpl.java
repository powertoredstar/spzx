package xiaoxin.spzx.product.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import xiaoxin.spzx.model.entity.product.Brand;
import xiaoxin.spzx.product.mapper.BrandMapper;
import xiaoxin.spzx.product.service.BrandService;

import java.util.List;

/**
 * ClassName: BrandServiceImpl
 * Package: xiaoxin.spzx.product.service.Impl
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 20:47
 * @Version 1.0
 */
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;
    @Override
    @Cacheable(value = "brandList",unless = "#result.size() == 0")
    public List<Brand> findAll() {
        return brandMapper.findAll();
    }
}
