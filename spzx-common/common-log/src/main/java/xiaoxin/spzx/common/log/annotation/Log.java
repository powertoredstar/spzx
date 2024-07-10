package xiaoxin.spzx.common.log.annotation;

import xiaoxin.spzx.common.log.enums.OperatorType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: Log
 * Package: xiaoxin.spzx.common.log.annotation
 * Description:
 *              自定义操作日志记录注解
 * @Author xiaoxin
 * @Create 2024/7/10 15:38
 * @Version 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    //模块名称
    public String title();
    //操作人员类别
    public OperatorType operatorType() default OperatorType.MANAGE;
    //业务类型(0其它  1新增  2修改 3删除)
    public int businessType();
    //是否保存请求的参数
    public boolean isSaveRequestData() default true;
    //是否保存响应的参数
    public boolean isSaveResponseData() default true;
}
