package xiaoxin.spzx.feign.cart;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import xiaoxin.spzx.model.entity.h5.CartInfo;
import xiaoxin.spzx.model.vo.common.Result;

import java.util.List;

/**
 * ClassName: CartFeignClient
 * Package: xiaoxin.spzx.feign.cart
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 10:20
 * @Version 1.0
 */
@FeignClient(value = "service-cart")
public interface CartFeignClient {
    @GetMapping(value = "/api/order/cart/auth/getAllChecked")
    public abstract List<CartInfo> getAllChecked();
    //清空选中的购物车数据
    @GetMapping(value = "/api/order/cart/auth/deleteChecked")
    public Result deleteChecked();
}
