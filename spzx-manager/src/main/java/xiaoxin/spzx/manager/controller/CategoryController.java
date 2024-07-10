package xiaoxin.spzx.manager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xiaoxin.spzx.manager.service.CategoryService;
import xiaoxin.spzx.model.entity.product.Category;
import xiaoxin.spzx.model.vo.common.Result;
import xiaoxin.spzx.model.vo.common.ResultCodeEnum;

import java.util.List;

/**
 * ClassName: CategoryController
 * Package: xiaoxin.spzx.manager.controller
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 9:10
 * @Version 1.0
 */
@Tag(name = "分类管理")
@RestController
@RequestMapping(value = "/admin/product/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Operation(summary = "根据parentId获取下级节点")
    @GetMapping(value = "/findByParentId/{parentId}")
    public Result<List<Category>> findByParentId(@PathVariable("parentId") Long parentId) {
        List<Category> list = categoryService.findByParentId(parentId);
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "导出数据")
    @GetMapping(value = "/exportData")
    public void exportData(HttpServletResponse response) {
        categoryService.exportData(response);
    }
    @Operation(summary = "导入功能")
    @PostMapping("importData")
    public Result importData(MultipartFile file) {
        categoryService.importData(file);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
