package xiaoxin.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import xiaoxin.spzx.model.dto.system.AssginRoleDto;
import xiaoxin.spzx.model.dto.system.SysRoleDto;
import xiaoxin.spzx.model.entity.system.SysRole;

import java.util.Map;

/**
 * ClassName: SysRoleService
 * Package: xiaoxin.spzx.manager.service
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/9 16:33
 * @Version 1.0
 */
public interface SysRoleService {
    /**
     * 分页查询角色信息
     * @param sysRoleDto
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, int pageNum, int pageSize);

    void saveSysRole(SysRole sysRole);

    void updateSysRole(SysRole sysRole);

    void deletedId(Long roleId);

    Map<String, Object> findAllRoles(Long userId);

    void doAssign(AssginRoleDto assginRoleDto);

}
