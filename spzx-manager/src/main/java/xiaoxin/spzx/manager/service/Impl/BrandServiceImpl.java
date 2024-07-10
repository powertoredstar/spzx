package xiaoxin.spzx.manager.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xiaoxin.spzx.manager.mapper.BrandMapper;
import xiaoxin.spzx.manager.service.BrandService;
import xiaoxin.spzx.model.entity.product.Brand;

import java.util.List;

/**
 * ClassName: BrandServiceImpl
 * Package: xiaoxin.spzx.manager.service.Impl
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 10:21
 * @Version 1.0
 */
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;
    @Override
    public PageInfo<Brand> findByPage(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Brand> brandList = brandMapper.findByPage();
        return new PageInfo(brandList);
    }

    @Override
    public void save(Brand brand) {
        brandMapper.save(brand);
    }

    @Override
    public void updateById(Brand brand) {
        brandMapper.updateById(brand);
    }

    @Override
    public void deleteById(Integer id) {
        brandMapper.deleteById(id);
    }

    @Override
    public List<Brand> findAll() {
        return brandMapper.findAll();
    }
}
