package xiaoxin.spzx.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import xiaoxin.spzx.model.entity.order.OrderLog;

/**
 * ClassName: OrderLogMapper
 * Package: xiaoxin.spzx.order.mapper
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 11:17
 * @Version 1.0
 */
@Mapper
public interface OrderLogMapper {
    void save(OrderLog orderLog);
}
