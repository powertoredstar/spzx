package xiaoxin.spzx.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xiaoxin.spzx.model.entity.product.Category;
import xiaoxin.spzx.model.entity.product.ProductSku;
import xiaoxin.spzx.model.vo.common.Result;
import xiaoxin.spzx.model.vo.common.ResultCodeEnum;
import xiaoxin.spzx.model.vo.h5.IndexVo;
import xiaoxin.spzx.product.service.CategoryService;
import xiaoxin.spzx.product.service.ProductService;

import java.util.List;

/**
 * ClassName: IndexController
 * Package: xiaoxin.spzx.product.controller
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 17:00
 * @Version 1.0
 */
@Tag(name = "首页接口管理")
@RestController
@RequestMapping(value = "/api/product/index")
@SuppressWarnings({"unchecked","rawtypes"})
public class IndexController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Operation(summary = "获取首页数据")
    @GetMapping
    public Result<IndexVo> findData(){
        List<Category> categoryList =  categoryService.findOneCategory();
        List<ProductSku> productSkuList = productService.findProductzSkuBySale();
        IndexVo indexVo = new IndexVo();
        indexVo.setCategoryList(categoryList);
        indexVo.setProductSkuList(productSkuList);
        return Result.build(indexVo, ResultCodeEnum.SUCCESS);
    }
}
