package xiaoxin.spzx.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import xiaoxin.spzx.model.entity.order.OrderInfo;

import java.util.List;

/**
 * ClassName: OrderInfoMapper
 * Package: xiaoxin.spzx.order.mapper
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 10:30
 * @Version 1.0
 */
@Mapper
public interface OrderInfoMapper {
    void save(OrderInfo orderInfo);

    OrderInfo getById(Long orderId);

    List<OrderInfo> findUserPage(Long userId, Integer orderStatus);
}
