package xiaoxin.spzx.manager.service;

import xiaoxin.spzx.model.dto.order.OrderStatisticsDto;
import xiaoxin.spzx.model.vo.order.OrderStatisticsVo;

/**
 * ClassName: OrderInfoService
 * Package: xiaoxin.spzx.manager.service
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 15:10
 * @Version 1.0
 */
public interface OrderInfoService {
    OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto);

}
