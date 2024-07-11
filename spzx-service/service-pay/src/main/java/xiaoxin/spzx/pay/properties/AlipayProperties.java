package xiaoxin.spzx.pay.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ClassName: AlipayProperties
 * Package: xiaoxin.spzx.pay.properties
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 14:08
 * @Version 1.0
 */
@Data
@ConfigurationProperties(prefix = "spzx.alipy")
public class AlipayProperties {
    private String alipayUrl;
    private String appPrivateKey;
    public  String alipayPublicKey;
    private String appId;
    public  String returnPaymentUrl;
    public  String notifyPaymentUrl;

    public final static String format="json";
    public final static String charset="utf-8";
    public final static String sign_type="RSA2";
}
