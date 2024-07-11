package xiaoxin.spzx.pay.service;

import org.springframework.stereotype.Service;

/**
 * ClassName: AlipayService
 * Package: xiaoxin.spzx.pay.service
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 14:11
 * @Version 1.0
 */
@Service
public interface AlipayService {
    String submitAlipay(String orderNo);
}
