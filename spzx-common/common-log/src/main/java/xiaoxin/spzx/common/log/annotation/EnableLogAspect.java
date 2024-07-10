package xiaoxin.spzx.common.log.annotation;

import org.springframework.context.annotation.Import;
import xiaoxin.spzx.common.log.aspect.LogAspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: EnableLogAspect
 * Package: xiaoxin.spzx.common.log.annotation
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 15:58
 * @Version 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(value = {LogAspect.class})
public @interface EnableLogAspect {
}
