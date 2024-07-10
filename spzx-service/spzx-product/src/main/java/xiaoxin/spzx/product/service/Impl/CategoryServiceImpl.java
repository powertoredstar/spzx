package xiaoxin.spzx.product.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import xiaoxin.spzx.model.entity.product.Category;
import xiaoxin.spzx.product.mapper.CategoryMapper;
import xiaoxin.spzx.product.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: CategoryServiceImpl
 * Package: xiaoxin.spzx.product.service.Impl
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 17:03
 * @Version 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public List<Category> findOneCategory() {
       return categoryMapper.findOneCategory();
    }

    @Override
    public List<Category> findCategoryTree() {
        List<Category> categoryList = categoryMapper.findAll();
        //全部一级分类
        List<Category> oneCategoryList = categoryList.stream()
                .filter(item -> item.getParentId().longValue() == 0)
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(oneCategoryList)) {
            oneCategoryList.forEach(oneCategory -> {
                List<Category> twoCategoryList = categoryList.stream()
                        .filter(item -> item.getParentId().longValue() == oneCategory.getId().longValue())
                        .collect(Collectors.toList());
                oneCategory.setChildren(twoCategoryList);
                if (!CollectionUtils.isEmpty(twoCategoryList)) {
                    twoCategoryList.forEach(twoCategory -> {
                        List<Category> threeCategoryList = categoryList.stream()
                                .filter(item -> item.getParentId().longValue() == twoCategory.getId().longValue())
                                .collect(Collectors.toList());
                        twoCategory.setChildren(threeCategoryList);
                    });
                }
            });
        }
        return oneCategoryList;
    }
}
