package xiaoxin.spzx.manager.service;

import xiaoxin.spzx.model.vo.system.ValidateCodeVo;

/**
 * ClassName: ValidateCodeService
 * Package: xiaoxin.spzx.manager.service
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/9 15:12
 * @Version 1.0
 */
public interface ValidateCodeService {
    /**
     * 获取验证码图片
     * @return
     */
    ValidateCodeVo generateValidateCode();

}
