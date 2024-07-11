package xiaoxin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xiaoxin.interceptor.UserLoginInterceptor;

/**
 * ClassName: UserWebMvcConfiguration
 * Package: xiaoxin.config
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 8:20
 * @Version 1.0
 */
// com.atguigu.spzx.common.config
@Configuration
public class UserWebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private UserLoginInterceptor userLoginAuthInterceptor ;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userLoginAuthInterceptor)
                .addPathPatterns("/api/**");
    }
}
