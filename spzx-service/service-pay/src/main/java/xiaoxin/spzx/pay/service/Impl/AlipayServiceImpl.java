package xiaoxin.spzx.pay.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xiaoxin.exception.XiaoxinException;
import xiaoxin.spzx.model.entity.pay.PaymentInfo;
import xiaoxin.spzx.model.vo.common.ResultCodeEnum;
import xiaoxin.spzx.pay.properties.AlipayProperties;
import xiaoxin.spzx.pay.service.AlipayService;
import xiaoxin.spzx.pay.service.PaymentInfoService;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * ClassName: AlipayServiceImpl
 * Package: xiaoxin.spzx.pay.service.Impl
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 14:11
 * @Version 1.0
 */
@Service
public class AlipayServiceImpl implements AlipayService {
    private static final Logger log = LoggerFactory.getLogger(AlipayServiceImpl.class);
    @Autowired
    private AlipayClient alipayClient;
    @Autowired
    private PaymentInfoService paymentInfoService;
    @Autowired
    private AlipayProperties alipayProperties;

    @SneakyThrows//lombok注解,对外声明异常
    @Override
    public String submitAlipay(String orderNo) {
        //保存支付记录
        PaymentInfo paymentInfo = paymentInfoService.savePaymentInfo(orderNo);
        //创建API对应的request
        AlipayTradeWapPayRequest payRequest = new AlipayTradeWapPayRequest();
        //同步回调
        payRequest.setReturnUrl(alipayProperties.getReturnPaymentUrl());
        //异步回调
        payRequest.setNotifyUrl(payRequest.getNotifyUrl());
        //准备请求参数,声明一个map集合
        HashMap<Object, Object> map = new HashMap<>();
        map.put("out_trade_no", paymentInfo.getOrderNo());
        map.put("product_code", "QUICK_WAP_WAY");
        map.put("total_amount", new BigDecimal("0.01"));
        payRequest.setBizContent(JSON.toJSONString(map));
        //发送请求
        AlipayTradeWapPayResponse response = alipayClient.pageExecute(payRequest);
        if(response.isSuccess()){
            log.info("调用成功");
            return response.getBody();
        }else{
            log.info("调用失败");
            throw new XiaoxinException(ResultCodeEnum.DATA_ERROR);
        }
    }
}
