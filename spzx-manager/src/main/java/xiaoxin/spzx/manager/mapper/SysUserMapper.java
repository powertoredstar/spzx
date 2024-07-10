package xiaoxin.spzx.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import xiaoxin.spzx.model.dto.system.SysUserDto;
import xiaoxin.spzx.model.entity.system.SysUser;

import java.util.List;

/**
 * ClassName: SysUserMapper
 * Package: xiaoxin.spzx.mapper
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/9 14:23
 * @Version 1.0
 */
@Mapper
public interface SysUserMapper {
    /**
     * 根据用户名查询用户数据
     * @param userName
     * @return
     */
    SysUser selectByUserName(String userName);

    List<SysUser> findByPage(SysUserDto sysUserDto);

    SysUser findByUserName(String userName);

    void saveSysUser(SysUser sysUser);

    void updateSysUser(SysUser sysUser);

    void deleteById(Integer userId);
}
