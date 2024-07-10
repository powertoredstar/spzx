package xiaoxin.spzx.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import xiaoxin.spzx.model.dto.order.OrderStatisticsDto;
import xiaoxin.spzx.model.entity.order.OrderStatistics;

import java.util.List;

/**
 * ClassName: OrderStatisticsMapper
 * Package: xiaoxin.spzx.manager.mapper
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 14:55
 * @Version 1.0
 */
@Mapper
public interface OrderStatisticsMapper {

    void insert(OrderStatistics orderStatistics);

    List<OrderStatistics> selectList(OrderStatisticsDto orderStatisticsDto);
}
