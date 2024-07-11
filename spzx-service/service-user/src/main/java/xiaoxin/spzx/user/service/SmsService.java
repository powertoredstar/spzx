package xiaoxin.spzx.user.service;

/**
 * ClassName: SmsService
 * Package: xiaoxin.spzx.user.service
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 21:56
 * @Version 1.0
 */
public interface SmsService {
    void sendValidateCode(String phone);
}
