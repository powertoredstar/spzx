package xiaoxin.spzx.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xiaoxin.spzx.model.dto.system.SysRoleDto;
import xiaoxin.spzx.model.entity.system.SysRole;

import java.util.List;

/**
 * ClassName: SysRoleMapper
 * Package: xiaoxin.spzx.manager.mapper
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/9 16:34
 * @Version 1.0
 */
@Mapper
public interface SysRoleMapper {
    List<SysRole> findByPage(SysRoleDto sysRoleDto);

    void saveSysRole(SysRole sysRole);

    void updateSysRole(SysRole sysRole);

    void deleteById(Long roleId);

    List<SysRole> findAllRoles();

    void deleteByUserId(Long userId);

    void dodoAssign(@Param("userId") Long userId,@Param("roleId") Long roleId);

    List<Long> findSysUserRoleByUserId(Long userId);
}
