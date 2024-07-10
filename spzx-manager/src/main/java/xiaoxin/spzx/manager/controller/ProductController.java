package xiaoxin.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xiaoxin.spzx.manager.service.ProductService;
import xiaoxin.spzx.model.dto.product.ProductDto;
import xiaoxin.spzx.model.entity.product.Product;
import xiaoxin.spzx.model.vo.common.Result;
import xiaoxin.spzx.model.vo.common.ResultCodeEnum;

/**
 * ClassName: ProductController
 * Package: xiaoxin.spzx.manager.controller
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 11:42
 * @Version 1.0
 */
@Tag(name = "商品管理")
@RestController
@RequestMapping(value = "/admin/product/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Operation(summary = "分页获取搜索商品")
    @GetMapping("/{page}/{limit}")
    public Result<PageInfo<Product>> list(@PathVariable Integer page, @PathVariable Integer limit, ProductDto productDto) {
        PageInfo<Product> pageInfo = productService.findByPage(page,limit,productDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "保存商品数据接口")
    @PostMapping("/save")
    public Result save(@RequestBody Product product) {
        productService.save(product);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "查询商品详情")
    @GetMapping("/getById/{id}")
    public Result<Product> getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        return Result.build(product, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "保存修改数据接口")
    @PutMapping("/updateById")
    public Result updateById(@Parameter(name = "product",description = "请求参数实体类",required = true) @RequestBody Product product) {
        productService.updateById(product);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "删除商品接口")
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@Parameter(name = "id",description = "商品id",required = true) @PathVariable Long id) {
        productService.deleteById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "商品审核")
    @GetMapping("/updateAuditStatus/{id}/{auditStatus}")
    public Result updateAuditStatus(@PathVariable Long id, @PathVariable Integer auditStatus) {
        productService.updateAuditStatus(id,auditStatus);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "商品上下架接口")
    @GetMapping("/updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id, @PathVariable Integer status) {
        productService.updateStatus(id,status);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
