package xiaoxin.spzx.manager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xiaoxin.spzx.manager.Interceptor.LoginAuthInterceptor;
import xiaoxin.spzx.manager.properties.UserAuthProperties;

/**
 * ClassName: WebMvcConfiguration
 * Package: xiaoxin.spzx.manager.config
 * Description:
 *              跨域配置类  注册拦截器
 * @Author xiaoxin
 * @Create 2024/7/9 15:08
 * @Version 1.0
 */
@Component
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Autowired
    private LoginAuthInterceptor loginAuthInterceptor;
    @Autowired
    private UserAuthProperties userAuthProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginAuthInterceptor)
                .excludePathPatterns(userAuthProperties.getNoAuthUrls())
                .addPathPatterns("/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOriginPatterns("*")
                .allowedHeaders("*")
                .allowedMethods("*");
    }
}
