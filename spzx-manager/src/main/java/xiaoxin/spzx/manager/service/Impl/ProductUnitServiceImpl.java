package xiaoxin.spzx.manager.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xiaoxin.spzx.manager.mapper.ProductUnitMapper;
import xiaoxin.spzx.manager.service.ProductUnitService;
import xiaoxin.spzx.model.entity.base.ProductUnit;

import java.util.List;

/**
 * ClassName: ProductUnitServiceImpl
 * Package: xiaoxin.spzx.manager.service.Impl
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 13:37
 * @Version 1.0
 */
@Service
public class ProductUnitServiceImpl implements ProductUnitService {
    @Autowired
    private ProductUnitMapper productUnitMapper;
    @Override
    public List<ProductUnit> findAll() {
        return productUnitMapper.findAll();
    }
}
