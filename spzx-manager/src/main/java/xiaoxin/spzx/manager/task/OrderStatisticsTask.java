package xiaoxin.spzx.manager.task;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import xiaoxin.spzx.manager.mapper.OrderInfoMapper;
import xiaoxin.spzx.manager.mapper.OrderStatisticsMapper;
import xiaoxin.spzx.model.entity.order.OrderStatistics;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName: OrderStatisticsTask
 * Package: xiaoxin.spzx.manager.task
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 14:53
 * @Version 1.0
 */
@Component
@Slf4j
public class OrderStatisticsTask {
    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Scheduled(cron = "0 0 2 * * ?")
    public void orderTotalAmountStatistics() {
        String createTime = DateUtil.offsetDay(new Date(), -1).toString(new SimpleDateFormat("yyyy-MM-dd"));
        OrderStatistics orderStatistics = orderInfoMapper.selectOrderStatistic(createTime);
        if(orderStatistics != null){
            orderStatisticsMapper.insert(orderStatistics);
        }
    }
}
