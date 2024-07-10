package xiaoxin.spzx.manager.service;

import xiaoxin.spzx.model.dto.system.AssginMenuDto;

import java.util.Map;

/**
 * ClassName: SysRoleMenuService
 * Package: xiaoxin.spzx.manager.service
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/9 21:15
 * @Version 1.0
 */
public interface SysRoleMenuService {
    Map<String, Object> findSysRoleMenuByRoleId(Long roleId);

    void doAssign(AssginMenuDto assginMenuDto);
}
