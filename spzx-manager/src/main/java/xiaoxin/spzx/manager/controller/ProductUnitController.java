package xiaoxin.spzx.manager.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xiaoxin.spzx.manager.service.ProductUnitService;
import xiaoxin.spzx.model.entity.base.ProductUnit;
import xiaoxin.spzx.model.vo.common.Result;
import xiaoxin.spzx.model.vo.common.ResultCodeEnum;

import java.util.List;

/**
 * ClassName: ProductUnitController
 * Package: xiaoxin.spzx.manager.controller
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 13:36
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/product/productUnit")
public class ProductUnitController {
    @Autowired
    private ProductUnitService productUnitService;
    @Operation(summary = "加载所有商品单元数据")
    @GetMapping("findAll")
    public Result<List<ProductUnit>> findAll(){
        List<ProductUnit> productUnitList = productUnitService.findAll();
        return Result.build(productUnitList, ResultCodeEnum.SUCCESS);
    }

}
