package xiaoxin.spzx.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import xiaoxin.spzx.model.entity.order.OrderStatistics;

/**
 * ClassName: OrderInfoMapper
 * Package: xiaoxin.spzx.manager.mapper
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 14:54
 * @Version 1.0
 */
@Mapper
public interface OrderInfoMapper {
    /**
     * 查询指定日期产生的订单数据
     * @param createTime
     * @return
     */
    OrderStatistics selectOrderStatistic(String createTime);

}
