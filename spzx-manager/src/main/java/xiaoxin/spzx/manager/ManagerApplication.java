package xiaoxin.spzx.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import xiaoxin.spzx.manager.config.MinioProperties;
import xiaoxin.spzx.manager.properties.UserAuthProperties;

/**
 * ClassName: ManagerApplication
 * Package: xiaoxin.spzx
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/9 14:20
 * @Version 1.0
 */
@SpringBootApplication
@EnableConfigurationProperties(value = {UserAuthProperties.class, MinioProperties.class})
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }
}
