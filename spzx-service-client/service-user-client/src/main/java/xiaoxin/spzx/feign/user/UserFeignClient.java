package xiaoxin.spzx.feign.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xiaoxin.spzx.model.entity.user.UserAddress;

/**
 * ClassName: UserFeignClient
 * Package: xiaoxin.spzx.feign.user
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 11:07
 * @Version 1.0
 */
@FeignClient(value = "service-user")
public interface UserFeignClient {
    @GetMapping("/api/user/userAddress/getUserAddress/{id}")
    public abstract UserAddress getUserAddress(@PathVariable Long id);
}
