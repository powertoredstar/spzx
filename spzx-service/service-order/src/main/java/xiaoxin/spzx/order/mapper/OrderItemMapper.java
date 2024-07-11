package xiaoxin.spzx.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import xiaoxin.spzx.model.entity.order.OrderItem;

import java.util.List;

/**
 * ClassName: OrderItemMapper
 * Package: xiaoxin.spzx.order.mapper
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 11:17
 * @Version 1.0
 */
@Mapper
public interface OrderItemMapper {
    void save(OrderItem orderItem);

    List<OrderItem> findByOrderId(Long id);
}
