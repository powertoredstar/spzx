package xiaoxin.interceptor;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import xiaoxin.spzx.model.entity.user.UserInfo;
import xiaoxin.spzx.utils.AuthContextUtil;

/**
 * ClassName: UserLoginInterceptor
 * Package: xiaoxin.interceptor
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 8:12
 * @Version 1.0
 */
@Component
public class UserLoginInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果token不为空，验证token的合法性
        String userInfoJson = redisTemplate.opsForValue().get("user:spzx:" + request.getHeader("token"));
        AuthContextUtil.setUserInfo(JSON.parseObject(userInfoJson, UserInfo.class));
        return true;
    }
}
