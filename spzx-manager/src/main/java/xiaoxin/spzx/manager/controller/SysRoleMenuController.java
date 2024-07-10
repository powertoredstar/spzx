package xiaoxin.spzx.manager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xiaoxin.spzx.manager.service.SysRoleMenuService;
import xiaoxin.spzx.model.dto.system.AssginMenuDto;
import xiaoxin.spzx.model.vo.common.Result;
import xiaoxin.spzx.model.vo.common.ResultCodeEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: SysRoleMenuController
 * Package: xiaoxin.spzx.manager.controller
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/9 21:14
 * @Version 1.0
 */
@Tag(name = "菜单管理接口")
@RestController
@RequestMapping(value = "/admin/system/sysRoleMenu")
public class SysRoleMenuController {
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Operation(summary = "查询菜单")
    @GetMapping(value = "/findSysRoleMenuByRoleId/{roledId}")
    public Result<Map<String, Object>> findSysRoleMenuByRoleId(@PathVariable(value = "roleId") Long roleId) {
        Map<String, Object> result = sysRoleMenuService.findSysRoleMenuByRoleId(roleId);
        return Result.build(result, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "保存菜单")
    @PostMapping("/doAssign")
    public Result doAssgin(@RequestBody AssginMenuDto assginMenuDto) {
        sysRoleMenuService.doAssign(assginMenuDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
