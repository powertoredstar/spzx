package xiaoxin.spzx.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xiaoxin.spzx.model.dto.h5.UserLoginDto;
import xiaoxin.spzx.model.dto.h5.UserRegisterDto;
import xiaoxin.spzx.model.entity.user.UserInfo;
import xiaoxin.spzx.model.vo.common.Result;
import xiaoxin.spzx.model.vo.common.ResultCodeEnum;
import xiaoxin.spzx.model.vo.h5.UserInfoVo;
import xiaoxin.spzx.user.service.UserInfoService;
import xiaoxin.spzx.utils.AuthContextUtil;

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
    @Operation(summary = "会员登录")
    @PostMapping("login")
    public Result login(@RequestBody UserLoginDto userLoginDto) {
        return Result.build(userInfoService.login(userLoginDto),ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "获取当前登录用户信息")
    @GetMapping("/auth/getCurrentUserInfo")
    public Result<UserInfo> getCurrentUserInfo(HttpServletRequest request) {
        UserInfo userInfo = AuthContextUtil.getUserInfo();
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(userInfo, userInfoVo);
        return Result.build(userInfoVo,ResultCodeEnum.SUCCESS);
    }

}
