package xiaoxin.spzx.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * ClassName: ProductApplication
 * Package: xiaoxin.spzx.product
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 16:56
 * @Version 1.0
 */
@SpringBootApplication
@EnableCaching
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
