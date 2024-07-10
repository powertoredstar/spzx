package xiaoxin.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import xiaoxin.spzx.model.dto.system.LoginDto;
import xiaoxin.spzx.model.dto.system.SysUserDto;
import xiaoxin.spzx.model.entity.system.SysUser;
import xiaoxin.spzx.model.vo.system.LoginVo;

/**
 * ClassName: SysUserService
 * Package: xiaoxin.spzx.service
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/9 14:22
 * @Version 1.0
 */
public interface SysUserService {
     PageInfo<SysUser> findByPage(SysUserDto sysUserDto, Integer pageNum, Integer pageSize);

    /**
     * 根据用户名查找用户数据
     * @param loginDto
     * @return
     */
    LoginVo login(LoginDto loginDto);

    SysUser getUserInfo(String token);

    /**
     * 退出登录
     * @param token
     */

    void logout(String token);

    void saveSysUser(SysUser sysUser);

    void updateSysUser(SysUser sysUser);

    void deleteById(Integer userId);
}
