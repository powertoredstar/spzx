package xiaoxin.spzx.cart.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xiaoxin.spzx.cart.service.CartService;
import xiaoxin.spzx.model.entity.h5.CartInfo;
import xiaoxin.spzx.model.vo.common.Result;
import xiaoxin.spzx.model.vo.common.ResultCodeEnum;

import java.util.List;

/**
 * ClassName: CartController
 * Package: xiaoxin.spzx.cart.controller
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 9:16
 * @Version 1.0
 */
@Tag(name = "购物车接口")
@RestController
@RequestMapping("/api/order/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Operation(summary = "添加购物车")
    @GetMapping("/auth/addToCart/{skuId}/{skuNum}")
    public Result addToCart(@Parameter(name = "skuId",description = "商品skuId",required = true) @PathVariable("skuId") Long skuId,
                            @Parameter(name = "skuNum",description = "数量",required = true) @PathVariable("skuNum") Integer skuNum){
        cartService.addToCart(skuId,skuNum);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "查询购物车")
    @GetMapping("auth/cartList")
    public Result<List<CartInfo>> cartList(){
        List<CartInfo> cartInfoList = cartService.getCartList();
        return Result.build(cartInfoList, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "删除购物车商品")
    @DeleteMapping("auth/deleteCart/{skuId}")
    public Result deleteCart(@Parameter(name = "skuId",description = "商品skuId",required = true) @PathVariable("skuId") Long skuId){
        cartService.deleteCart(skuId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "更新购物车商品选中状态")
    @GetMapping("/auth/checkCart/{skuId}/{isChecked}")
    public Result checkCart(@Parameter(name = "skuId",description = "商品skuId",required = true) @PathVariable Long skuId,
                            @Parameter(name = "isChecked",description = "是否选中 1:选中 0:取消选中",required = true) @PathVariable(value = "isChecked") Integer isChecked){
        cartService.checkCart(skuId,isChecked);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "更新购物车商品全部选中状态")
    @GetMapping("/auth/allCheckCart/{isChecked}")
    public  Result allCheckCart(@Parameter(name = "isChecked" ,description = "是否选中 1:选中 0:取消选中",required = true) @PathVariable(value = "isChecked") Integer isChecked){
        cartService.allCheckCart(isChecked);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "清空购物车")
    @GetMapping("/auth/clearCart")
    public Result clearCart(){
        cartService.clearCart();
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "选中的购物车")
    @GetMapping(value = "/auth/getAllChecked")
    public List<CartInfo> getAllChecked(){
        List<CartInfo> cartInfoList = cartService.getAllChecked();
        return cartInfoList;
    }
    @Operation(summary = "清空购物车")
    @GetMapping(value = "/auth/deleteChecked")
    public Result deleteChecked(){
        cartService.deleteChecked();
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
