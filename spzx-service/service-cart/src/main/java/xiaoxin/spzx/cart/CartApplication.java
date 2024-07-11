package xiaoxin.spzx.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import xiaoxin.annoatation.EnableUserWebMvcConfiguration;

/**
 * ClassName: CartApplication
 * Package: xiaoxin.spzx.cart
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 8:39
 * @Version 1.0
 */
@EnableUserWebMvcConfiguration
@EnableFeignClients(basePackages = {"xiaoxin.spzx.feign.product"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})//排除数据库的自动化配置,Cart微服务不需要访问数据库
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class, args);
    }
}
