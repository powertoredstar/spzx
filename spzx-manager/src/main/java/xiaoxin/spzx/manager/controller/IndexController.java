package xiaoxin.spzx.manager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xiaoxin.spzx.manager.service.SysMenuService;
import xiaoxin.spzx.manager.service.SysUserService;
import xiaoxin.spzx.manager.service.ValidateCodeService;
import xiaoxin.spzx.model.dto.system.LoginDto;
import xiaoxin.spzx.model.entity.system.SysMenu;
import xiaoxin.spzx.model.entity.system.SysUser;
import xiaoxin.spzx.model.vo.common.Result;
import xiaoxin.spzx.model.vo.common.ResultCodeEnum;
import xiaoxin.spzx.model.vo.h5.UserInfoVo;
import xiaoxin.spzx.model.vo.system.LoginVo;
import xiaoxin.spzx.model.vo.system.SysMenuVo;
import xiaoxin.spzx.model.vo.system.ValidateCodeVo;
import xiaoxin.spzx.utils.AuthContextUtil;

import java.util.List;

/**
 * ClassName: IndexController
 * Package: xiaoxin.spzx.controller
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/9 14:21
 * @Version 1.0
 */
@Tag(name = "用户接口")
@RestController
@RequestMapping(value = "/admin/system/index")
//@CrossOrigin(allowCredentials = "true",originPatterns = "*",allowedHeaders = "*")
public class IndexController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ValidateCodeService validateCodeService;
    @Autowired
    private SysMenuService sysMenuService;
    @Operation(summary = "登录接口")
    @PutMapping(value = "/login")
    public Result<LoginVo> login(@RequestBody LoginDto loginDto) {
        LoginVo loginVo = sysUserService.login(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "获取验证码接口")
    @GetMapping(value = "/generateValidateCode")
    public Result<ValidateCodeVo> generateValidateCode() {
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();
        return Result.build(validateCodeVo, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "获取用户登录信息接口")
    @GetMapping(value = "/getUserInfo")
    public Result< SysUser> getUserInfo() {
        return Result.build(AuthContextUtil.get(), ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "退出登录接口")
    public Result logout(@RequestHeader(name = "token") String token) {
        sysUserService.logout(token);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "根据用户状态获取菜单接口")
    @GetMapping("/menus")
    public Result menus() {
        List<SysMenuVo> sysMenuVoList = sysMenuService.findUserMenuList();
        return Result.build(sysMenuVoList, ResultCodeEnum.SUCCESS);
    }

}
