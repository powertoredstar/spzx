package xiaoxin.spzx.manager.service.Impl;

import cn.hutool.core.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xiaoxin.spzx.manager.mapper.OrderStatisticsMapper;
import xiaoxin.spzx.manager.service.OrderInfoService;
import xiaoxin.spzx.model.dto.order.OrderStatisticsDto;
import xiaoxin.spzx.model.entity.order.OrderStatistics;
import xiaoxin.spzx.model.vo.order.OrderStatisticsVo;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: OrderInfoServiceImpl
 * Package: xiaoxin.spzx.manager.service.Impl
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 15:10
 * @Version 1.0
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;
    @Override
    public OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto) {
        //1.查询统计结果数据
       List<OrderStatistics> orderStatisticsList = orderStatisticsMapper.selectList(orderStatisticsDto);
        //2.日期列表
        List<String> dateList = orderStatisticsList.stream()
                .map(orderStatistics -> DateUtil.format(orderStatistics.getOrderDate(),"yyyy-MM-dd"))
                .collect(Collectors.toList());
        //3.统计金额列表
        List<BigDecimal> amountList = orderStatisticsList.stream()
                .map(OrderStatistics::getTotalAmount)
                .collect(Collectors.toList());
        //创建OrderStatisticsVo对象封装响应结果数据
        OrderStatisticsVo orderStatisticsVo = new OrderStatisticsVo();
        orderStatisticsVo.setDateList(dateList);
        orderStatisticsVo.setAmountList(amountList);
        //返回数据
        return orderStatisticsVo;
    }
}
