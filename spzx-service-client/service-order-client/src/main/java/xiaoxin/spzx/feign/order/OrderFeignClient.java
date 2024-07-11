package xiaoxin.spzx.feign.order;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xiaoxin.spzx.model.entity.order.OrderInfo;
import xiaoxin.spzx.model.vo.common.Result;

/**
 * ClassName: OrderFeignClient
 * Package: xiaoxin.spzx.feign.order
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 13:49
 * @Version 1.0
 */
@FeignClient(value = "service-order")
public interface OrderFeignClient {
    @GetMapping("/api/order/orderInfo/auth/getOrderInfoByOrderNo/{orderNo}")
    public Result<OrderInfo> getOrderInfoByOrderNo(@PathVariable String orderNo);
    @GetMapping("/api/order/orderInfo/auth/updateOrderStatusPayed/{orderNo}/{orderStatus}")
    public Result updateOrderStatus(@PathVariable(value = "orderNo") String orderNo,@PathVariable(value = "orderStatus") Integer orderStatus);


}
