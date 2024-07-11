package xiaoxin.spzx.pay.service;

import xiaoxin.spzx.model.entity.pay.PaymentInfo;

import java.util.Map;

/**
 * ClassName: PaymentInfoService
 * Package: xiaoxin.spzx.pay.service
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 13:55
 * @Version 1.0
 */
public interface PaymentInfoService {
    PaymentInfo savePaymentInfo(String orderNo);

    void updatePaymentStatus(Map<String, String> paramMap, Integer payType);
}
