package xiaoxin.spzx.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import xiaoxin.annoatation.EnableUserTokenFeignInterceptor;
import xiaoxin.annoatation.EnableUserWebMvcConfiguration;

/**
 * ClassName: OrderApplication
 * Package: xiaoxin.spzx.order
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 10:25
 * @Version 1.0
 */
@SpringBootApplication
@EnableUserTokenFeignInterceptor
@EnableUserWebMvcConfiguration
@EnableFeignClients(basePackages = {"xiaoxin.spzx.feign.cart",
        "xiaoxin.spzx.feign.user","xiaoxin.spzx.feign.product"})
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
