package xiaoxin.annoatation;

import org.springframework.context.annotation.Import;
import xiaoxin.feign.UserTokenFeignInterceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: EnableUserTokenFeignInterceptor
 * Package: xiaoxin.annoatation
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 10:51
 * @Version 1.0
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Import(value = UserTokenFeignInterceptor.class)
public @interface EnableUserTokenFeignInterceptor {
}
