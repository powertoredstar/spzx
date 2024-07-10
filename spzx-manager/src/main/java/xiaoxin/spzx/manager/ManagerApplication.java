package xiaoxin.spzx.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import xiaoxin.spzx.common.log.annotation.EnableLogAspect;
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
@EnableScheduling//开启定时任务功能
@EnableLogAspect
@EnableAsync
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }
}
