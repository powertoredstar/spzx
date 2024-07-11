package xiaoxin.spzx.order.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xiaoxin.annoatation.EnableUserTokenFeignInterceptor;
import xiaoxin.spzx.model.dto.h5.OrderInfoDto;
import xiaoxin.spzx.model.entity.order.OrderInfo;
import xiaoxin.spzx.model.vo.common.Result;
import xiaoxin.spzx.model.vo.common.ResultCodeEnum;
import xiaoxin.spzx.model.vo.h5.TradeVo;
import xiaoxin.spzx.order.service.OrderInfoService;

/**
 * ClassName: OrderController
 * Package: xiaoxin.spzx.order.controller
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 10:27
 * @Version 1.0
 */
@Tag(name = "订单管理")
@RestController
@RequestMapping(value = "/api/order/orderInfo")
@SuppressWarnings({"unchecked","rawtypes"})
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderInfoService;

    @Operation(summary = "确认下单")
    @GetMapping("auth/trade")
    public Result<TradeVo> trade() {
        TradeVo tradeVo = orderInfoService.getTrade();
        return Result.build(tradeVo, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "提交订单")
    @PostMapping("auth/submitOrder")
    public Result<Long> submitOrder(@Parameter(name = "orderInfoDto",description = "请求参数实体类",required = true) @RequestBody OrderInfoDto orderInfoDto){
       Long orderId = orderInfoService.submitOrder(orderInfoDto);
       return Result.build(orderId,ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "获取订单信息")
    @GetMapping("auth/{orderId}")
    public Result<OrderInfo> getOrderInfo(@Parameter(name = "orderId",description = "订单id",required = true) @PathVariable("orderId") Long orderId){
        OrderInfo orderInfo = orderInfoService.getOrderInfo(orderId);
        return Result.build(orderInfo,ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "立即购买")
    @GetMapping("auth/buy/{skuId}")
    public Result<TradeVo> buy(@Parameter(name = "skuId",description = "商品skuId",required = true) @PathVariable("skuId") Long skuId){
        TradeVo tradeVo = orderInfoService.buy(skuId);
        return Result.build(tradeVo,ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "获取订单分页列表")
    @GetMapping("auth/{page}/{limit}")
    public Result<PageInfo<OrderInfo>> list(
            @Parameter(name = "page",description = "当前页码",required = true) @PathVariable Integer page,
            @Parameter(name = "limit",description = "每页记录数",required = true) @PathVariable Integer limit,
            @Parameter(name = "orderStatus",description = "订单状态",required = true) @RequestParam Integer orderStatus
    ){
        PageInfo<OrderInfo> pageInfo = orderInfoService.findUserPage(page,limit,orderStatus);
        return Result.build(pageInfo,ResultCodeEnum.SUCCESS);
    }
}
