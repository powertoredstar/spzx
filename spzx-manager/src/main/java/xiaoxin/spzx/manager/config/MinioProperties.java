package xiaoxin.spzx.manager.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ClassName: MinioProperties
 * Package: xiaoxin.spzx.manager.config
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/9 19:31
 * @Version 1.0
 */
@Data
@ConfigurationProperties(prefix = "spzx.minio")//读取节点
public class MinioProperties {
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucket;
}
