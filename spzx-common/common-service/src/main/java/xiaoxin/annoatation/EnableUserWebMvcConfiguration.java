package xiaoxin.annoatation;

import org.springframework.context.annotation.Import;
import xiaoxin.config.UserWebMvcConfiguration;
import xiaoxin.interceptor.UserLoginInterceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: EnableUserWebMvcConfiguration
 * Package: xiaoxin.annoatation
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 8:22
 * @Version 1.0
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Import(value = { UserWebMvcConfiguration.class, UserLoginInterceptor.class})
public @interface EnableUserWebMvcConfiguration {
}
