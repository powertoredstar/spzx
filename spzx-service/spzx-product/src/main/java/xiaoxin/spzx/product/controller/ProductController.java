package xiaoxin.spzx.product.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xiaoxin.spzx.model.dto.h5.ProductSkuDto;
import xiaoxin.spzx.model.entity.product.ProductSku;
import xiaoxin.spzx.model.vo.common.Result;
import xiaoxin.spzx.model.vo.common.ResultCodeEnum;
import xiaoxin.spzx.model.vo.h5.ProductItemVo;
import xiaoxin.spzx.product.service.ProductService;

/**
 * ClassName: ProductController
 * Package: xiaoxin.spzx.product.controller
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 20:57
 * @Version 1.0
 */
@Tag(name = "商品列表管理")
@RestController
@RequestMapping(value = "/api/product")
@SuppressWarnings({"unchecked","rawtypes"})
public class ProductController {
    @Autowired
    private ProductService productService;
    @Operation(summary = "分页查询")
    @GetMapping(value = "/{page}/{limit}")
    public Result<PageInfo<ProductSku>> findByPage(@Parameter(name = "page",description = "当前页码",required = true) @PathVariable("page") Integer page,
                                                   @Parameter(name = "limit",description = "每页记录数",required = true) @PathVariable Integer limit,
                                                   @Parameter(name = "productSkuDto",description = "搜索条件对象",required = false)ProductSkuDto productSkuDto) {
        PageInfo<ProductSku> pageInfo = productService.findByPage(page,limit,productSkuDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "商品详情")
    @GetMapping("item/{skuId}")
    public Result<ProductItemVo> item(@Parameter(name = "skuId",description = "商品skuId",required = true) @PathVariable Long skuId){
        ProductItemVo productItemVo = productService.item(skuId);
        return Result.build(productItemVo, ResultCodeEnum.SUCCESS);
    }

}
