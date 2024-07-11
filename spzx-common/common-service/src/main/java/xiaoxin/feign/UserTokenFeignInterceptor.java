package xiaoxin.feign;

import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * ClassName: UserTokenFeignInterceptor
 * Package: xiaoxin.feign
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 10:46
 * @Version 1.0
 */
public class UserTokenFeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //拿到aysnc请求里的token
        String token = request.getHeader("token");
        //将token传入openFeign转发请求
        requestTemplate.header("token", token);
    }
}
