package xiaoxin.spzx.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xiaoxin.spzx.model.entity.product.Brand;
import xiaoxin.spzx.model.vo.common.Result;
import xiaoxin.spzx.model.vo.common.ResultCodeEnum;
import xiaoxin.spzx.product.service.BrandService;

import java.util.List;

/**
 * ClassName: BrandController
 * Package: xiaoxin.spzx.product.controller
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 20:46
 * @Version 1.0
 */
@Tag(name = "品牌管理")
@RestController
@RequestMapping(value = "/api/product/brand")
@SuppressWarnings({"unchecked","rawtypes"})
public class BrandController {
    @Autowired
    private BrandService brandService;
    @Operation(summary = "获取全部品牌")
    @GetMapping("findAll")
    public Result<List<Brand>> findAll() {
        List<Brand> list = brandService.findAll();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
}
