package xiaoxin.spzx.manager.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xiaoxin.spzx.manager.mapper.ProductSpecMapper;
import xiaoxin.spzx.manager.service.ProductSpecService;
import xiaoxin.spzx.model.entity.product.ProductSpec;

import java.util.List;

/**
 * ClassName: ProductSpecServiceImpl
 * Package: xiaoxin.spzx.manager.service.Impl
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 11:21
 * @Version 1.0
 */
@Service
public class ProductSpecServiceImpl implements ProductSpecService {
    @Autowired
    private ProductSpecMapper productSpecMapper;
    @Override
    public PageInfo<ProductSpec> findByPage(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<ProductSpec> productSpecList = productSpecMapper.findByPage();
        return new PageInfo<>(productSpecList);
    }

    @Override
    public void save(ProductSpec productSpec) {
        productSpecMapper.save(productSpec);
    }

    @Override
    public void updateById(ProductSpec productSpec) {
        productSpecMapper.updateById(productSpec);
    }

    @Override
    public void deleteById(Long id) {
        productSpecMapper.deleteById(id);
    }

    @Override
    public List<ProductSpec> findAll() {
        return productSpecMapper.findAll();
    }
}
