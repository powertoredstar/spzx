package xiaoxin.spzx.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xiaoxin.spzx.model.vo.common.Result;
import xiaoxin.spzx.model.vo.common.ResultCodeEnum;
import xiaoxin.spzx.user.service.SmsService;

/**
 * ClassName: SmsController
 * Package: xiaoxin.spzx.user.controller
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 21:55
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/user/sms")
public class SmsController {
    @Autowired
    private SmsService smsService;
    @GetMapping(value = "/sendCode/{phone}")
    public Result sendValidateCode(@PathVariable("phone") String phone) {
        smsService.sendValidateCode(phone);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
