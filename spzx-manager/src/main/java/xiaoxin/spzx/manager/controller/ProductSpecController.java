package xiaoxin.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xiaoxin.spzx.manager.service.ProductSpecService;
import xiaoxin.spzx.model.entity.product.ProductSpec;
import xiaoxin.spzx.model.vo.common.Result;
import xiaoxin.spzx.model.vo.common.ResultCodeEnum;

import java.util.List;

/**
 * ClassName: ProductSpecController
 * Package: xiaoxin.spzx.manager.controller
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 11:19
 * @Version 1.0
 */
@Tag(name = "商品规格管理")
@RestController
@RequestMapping(value = "/admin/product/productSpec")
public class ProductSpecController {
    @Autowired
    private ProductSpecService productSpecService;
    @Operation(summary = "获取分页商品规格")
    @GetMapping("/{page}/{limit}")
    public Result<PageInfo<ProductSpec>> findByPage(@PathVariable Integer page, @PathVariable Integer limit){
        PageInfo<ProductSpec> pageInfo = productSpecService.findByPage(page,limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "添加商品规格")
    @PostMapping("save")
    public Result save(@RequestBody ProductSpec productSpec){
        productSpecService.save(productSpec);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "修改商品规格")
    @PutMapping("updateById")
    public Result updateById(@RequestBody ProductSpec productSpec){
        productSpecService.updateById(productSpec);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "删除商品规格")
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Long id){
        productSpecService.deleteById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "加载商品规格数据")
    @GetMapping("findAll")
    public Result findAll(){
        List<ProductSpec> list = productSpecService.findAll();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
}
