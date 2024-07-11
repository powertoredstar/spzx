package xiaoxin.spzx.pay.service.Impl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xiaoxin.spzx.feign.order.OrderFeignClient;
import xiaoxin.spzx.feign.product.ProductFeignClient;
import xiaoxin.spzx.model.dto.product.SkuSaleDto;
import xiaoxin.spzx.model.entity.order.OrderInfo;
import xiaoxin.spzx.model.entity.order.OrderItem;
import xiaoxin.spzx.model.entity.pay.PaymentInfo;
import xiaoxin.spzx.model.vo.common.Result;
import xiaoxin.spzx.pay.mapper.PaymentInfoMapper;
import xiaoxin.spzx.pay.service.PaymentInfoService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClassName: PaymentInfoServiceImpl
 * Package: xiaoxin.spzx.pay.service.Impl
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 13:55
 * @Version 1.0
 */
@Service
public class PaymentInfoServiceImpl implements PaymentInfoService {
    @Autowired
    private PaymentInfoMapper paymentInfoMapper;
    @Autowired
    private OrderFeignClient orderFeignClient;
    @Autowired
    private ProductFeignClient productFeignClient;

    @Override
    public PaymentInfo savePaymentInfo(String orderNo) {
        //查询支付信息数据,如果已经存在了就不用进行保存(一个订单支付失败以后可以继续支付)
        PaymentInfo paymentInfo = paymentInfoMapper.getByOrderNo(orderNo);
        if (paymentInfo == null) {
            OrderInfo orderInfo = orderFeignClient.getOrderInfoByOrderNo(orderNo).getData();
            paymentInfo = new PaymentInfo();
            paymentInfo.setUserId(orderInfo.getUserId());
            paymentInfo.setPayType(orderInfo.getPayType());
            String content = "";
            for (OrderItem item : orderInfo.getOrderItemList()) {
                content += item.getSkuName() + " ";
            }
            paymentInfo.setContent(content);
            paymentInfo.setOrderNo(orderNo);
            paymentInfo.setAmount(orderInfo.getTotalAmount());
            paymentInfo.setPaymentStatus(0);
            paymentInfoMapper.save(paymentInfo);
        }
        return paymentInfo;
    }
    @Transactional
    @Override
    public void updatePaymentStatus(Map<String, String> paramMap, Integer payType) {
        //查询paymentInfo
        PaymentInfo paymentInfo = paymentInfoMapper.getByOrderNo(paramMap.get("out_trade_no"));
        if(paymentInfo.getPaymentStatus() == 1){
            return;
        }
        //更新支付信息
        paymentInfo.setPaymentStatus(1);
        paymentInfo.setOutTradeNo(paramMap.get("out_trade_no"));
        paymentInfo.setCallbackTime(new Date());
        paymentInfo.setCallbackContent(JSON.toJSONString(paramMap));
        paymentInfoMapper.updateById(paymentInfo);
        //更新订单支付状态

        orderFeignClient.updateOrderStatus(paymentInfo.getOrderNo(),payType);

        //更新商品销量
       Result<OrderInfo> orderInfoByOrderNo = orderFeignClient.getOrderInfoByOrderNo(paymentInfo.getOrderNo());

        OrderInfo orderInfo = orderInfoByOrderNo.getData();
        List<SkuSaleDto> skuSaleDtoList = orderInfo.getOrderItemList().stream()
                .map(item -> {
                    SkuSaleDto skuSaleDto = new SkuSaleDto();
                    skuSaleDto.setSkuId(item.getSkuId());
                    skuSaleDto.setNum(item.getSkuNum());
                    return skuSaleDto;
                }).collect(Collectors.toList());
        productFeignClient.updateSkuSaleNum(skuSaleDtoList);

    }
}
