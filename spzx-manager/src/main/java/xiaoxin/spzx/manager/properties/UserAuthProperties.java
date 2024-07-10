package xiaoxin.spzx.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * ClassName: UserAuthProperties
 * Package: xiaoxin.spzx.manager.properties
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/9 16:08
 * @Version 1.0
 */
@Data
//前缀不能使用驼峰命名
@ConfigurationProperties(prefix = "spzx.auth")
public class UserAuthProperties {
    private List<String> noAuthUrls;
}
