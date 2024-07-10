package xiaoxin.spzx.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xiaoxin.spzx.model.entity.product.Category;
import xiaoxin.spzx.model.vo.common.Result;
import xiaoxin.spzx.model.vo.common.ResultCodeEnum;
import xiaoxin.spzx.product.service.CategoryService;

import java.util.List;

/**
 * ClassName: CategoryController
 * Package: xiaoxin.spzx.product.controller
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 17:34
 * @Version 1.0
 */
@Tag(name = "分类接口管理")
@RestController
@RequestMapping(value = "/api/product/category")
@SuppressWarnings({"unchecked", "rawtypes"})
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Operation(summary = "获得分类树形数据")
    @GetMapping("findCategoryTree")
    public Result<List<Category>> findCategoryTree() {
        List<Category> list = categoryService.findCategoryTree();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
}
