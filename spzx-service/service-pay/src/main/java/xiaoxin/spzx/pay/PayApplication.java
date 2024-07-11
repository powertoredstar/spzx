package xiaoxin.spzx.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import xiaoxin.annoatation.EnableUserWebMvcConfiguration;
import xiaoxin.spzx.pay.properties.AlipayProperties;

/**
 * ClassName: PayApplication
 * Package: xiaoxin.spzx.pay
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 13:43
 * @Version 1.0
 */
@SpringBootApplication
@EnableUserWebMvcConfiguration
@EnableConfigurationProperties(value = {AlipayProperties.class})
@EnableFeignClients(basePackages = {"xiaoxin.spzx.feign.order"})
public class PayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
    }
}
