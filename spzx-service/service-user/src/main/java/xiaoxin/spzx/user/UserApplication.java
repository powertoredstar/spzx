package xiaoxin.spzx.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xiaoxin.annoatation.EnableUserWebMvcConfiguration;

/**
 * ClassName: UserApplication
 * Package: xiaoxin.spzx.user
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 21:54
 * @Version 1.0
 */
@SpringBootApplication
@EnableUserWebMvcConfiguration
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
