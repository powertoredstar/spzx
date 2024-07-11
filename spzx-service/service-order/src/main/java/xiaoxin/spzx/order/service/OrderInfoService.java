package xiaoxin.spzx.order.service;

import com.github.pagehelper.PageInfo;
import xiaoxin.spzx.model.dto.h5.OrderInfoDto;
import xiaoxin.spzx.model.entity.order.OrderInfo;
import xiaoxin.spzx.model.vo.h5.TradeVo;

/**
 * ClassName: OrderInfoService
 * Package: xiaoxin.spzx.order.service
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 10:30
 * @Version 1.0
 */
public interface OrderInfoService {
    TradeVo getTrade();

    Long submitOrder(OrderInfoDto orderInfoDto);

    OrderInfo getOrderInfo(Long orderId);

    TradeVo buy(Long skuId);

    PageInfo<OrderInfo> findUserPage(Integer page, Integer limit, Integer orderStatus);

    OrderInfo getByOrderNo(String orderNo);

    void updateOrderStatus(String orderNo, Integer orderStatus);
}
