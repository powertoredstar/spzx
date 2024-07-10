package xiaoxin.Handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import xiaoxin.exception.XiaoxinException;
import xiaoxin.spzx.model.vo.common.Result;

/**
 * ClassName: GlobalExceptionHandler
 * Package: xiaoxin.Handler
 * Description:
 *              统一异常处理类
 * @Author xiaoxin
 * @Create 2024/7/9 14:45
 * @Version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.build(null,201,"出现了异常");
    }
    @ExceptionHandler(value = XiaoxinException.class)
    @ResponseBody
    public Result error(XiaoxinException e) {
        e.printStackTrace();
        return Result.build(null,e.getResultCodeEnum());
    }

}
