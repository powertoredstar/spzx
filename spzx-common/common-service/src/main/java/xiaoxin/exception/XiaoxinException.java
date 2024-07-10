package xiaoxin.exception;

import lombok.Data;
import xiaoxin.spzx.model.vo.common.ResultCodeEnum;

/**
 * ClassName: XiaoxinException
 * Package: xiaoxin.exception
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/9 14:47
 * @Version 1.0
 */
@Data
public class XiaoxinException extends RuntimeException{
    //错误状态码
    private Integer code;
    //错误消息
    private String message;
    //封装错误状态码和消息
    private ResultCodeEnum resultCodeEnum;
    public XiaoxinException(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum;
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }
    public XiaoxinException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
