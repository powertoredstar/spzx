package xiaoxin.spzx.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xiaoxin.spzx.model.dto.h5.UserRegisterDto;
import xiaoxin.spzx.model.vo.common.Result;
import xiaoxin.spzx.model.vo.common.ResultCodeEnum;
import xiaoxin.spzx.user.service.UserInfoService;

/**
 * ClassName: UserInfoController
 * Package: xiaoxin.spzx.user.controller
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 22:18
 * @Version 1.0
 */
@Tag(name = "会员用户接口")
@RestController
@RequestMapping("/api/user/userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @Operation(summary = "会员注册")
    @PostMapping("register")
    public Result register(@RequestBody UserRegisterDto userRegisterDto) {
        userInfoService.register(userRegisterDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

}
