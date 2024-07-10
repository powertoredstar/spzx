package xiaoxin.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import io.github.classgraph.PackageInfo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xiaoxin.spzx.common.log.annotation.Log;
import xiaoxin.spzx.common.log.enums.OperatorType;
import xiaoxin.spzx.manager.service.BrandService;
import xiaoxin.spzx.model.entity.product.Brand;
import xiaoxin.spzx.model.vo.common.Result;
import xiaoxin.spzx.model.vo.common.ResultCodeEnum;

import java.util.List;

/**
 * ClassName: BrandController
 * Package: xiaoxin.spzx.manager.controller
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 10:20
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/admin/product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;
    @Operation(summary = "获取品牌")
    @GetMapping("/{page}/{limit}")
    @Log(title = "品牌列表",businessType = 0,operatorType = OperatorType.MANAGE)
    public Result<PageInfo<Brand>> findByPage(@PathVariable Integer page,@PathVariable Integer limit){
        PageInfo<Brand> pageInfo = brandService.findByPage(page,limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "保存品牌")
    @PostMapping("save")
    public Result save(@RequestBody Brand brand){
        brandService.save(brand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "修改品牌")
    @PutMapping("updateById")
    public Result updateById(@RequestBody Brand brand){
        brandService.updateById(brand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "删除品牌")
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Integer id){
        brandService.deleteById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "获取所有品牌")
    @GetMapping("/findAll")
    public Result findAll(){
        List<Brand> list = brandService.findAll();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
}
