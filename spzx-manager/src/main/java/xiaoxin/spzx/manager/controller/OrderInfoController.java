package xiaoxin.spzx.manager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xiaoxin.spzx.manager.service.OrderInfoService;
import xiaoxin.spzx.model.dto.order.OrderStatisticsDto;
import xiaoxin.spzx.model.vo.common.Result;
import xiaoxin.spzx.model.vo.common.ResultCodeEnum;
import xiaoxin.spzx.model.vo.order.OrderStatisticsVo;

/**
 * ClassName: OrderInfoController
 * Package: xiaoxin.spzx.manager.controller
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 15:09
 * @Version 1.0
 */
@Tag(name = "统计数据接口")
@RestController
@RequestMapping(value = "/admin/order/orderInfo")
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderInfoService;
    @Operation("统计查询接口")
    @GetMapping("/getOrderStatisticsData")
    public Result<OrderStatisticsVo> getOrderStatisticsData(@RequestBody OrderStatisticsDto orderStatisticsDto){
        OrderStatisticsVo orderStatisticsVo = orderInfoService.getOrderStatisticsData(orderStatisticsDto);
        return Result.build(orderStatisticsVo, ResultCodeEnum.SUCCESS);
    }
}
