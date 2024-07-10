package xiaoxin.spzx.manager.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xiaoxin.spzx.manager.mapper.SysRoleMapper;
import xiaoxin.spzx.manager.service.SysRoleService;
import xiaoxin.spzx.model.dto.system.AssginRoleDto;
import xiaoxin.spzx.model.dto.system.SysRoleDto;
import xiaoxin.spzx.model.entity.system.SysRole;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: SysRoleServiceImpl
 * Package: xiaoxin.spzx.manager.service.Impl
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/9 16:34
 * @Version 1.0
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Override
    public PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, int pageNum, int pageSize) {
         PageHelper.startPage(pageNum, pageSize);
        List<SysRole> sysRoleList = sysRoleMapper.findByPage(sysRoleDto);
        PageInfo<SysRole> pageInfo = new PageInfo<>(sysRoleList);
        return pageInfo;
    }

    @Override
    public void saveSysRole(SysRole sysRole) {
        sysRoleMapper.saveSysRole(sysRole);
    }

    @Override
    public void updateSysRole(SysRole sysRole) {
        sysRoleMapper.updateSysRole(sysRole);
    }

    @Override
    public void deletedId(Long roleId) {
        sysRoleMapper.deleteById(roleId);
    }

    @Override
    public Map<String, Object> findAllRoles(Long userId) {
        //查询所有的角色数据
       List<SysRole> sysRoleList =  sysRoleMapper.findAllRoles();
       //查询当前登录用户的角色数据
        List<Long> sysRoles = sysRoleMapper.findSysUserRoleByUserId(userId)
        //构造响应结果数据
       Map<String, Object> map = new HashMap<String, Object>();
       map.put("allRoleList", sysRoleList);
       map.put("sysUserRoles", sysRoles);
       return map;
    }
    @Transactional
    @Override
    public void doAssign(AssginRoleDto assginRoleDto) {
        //删除之前的所有用户对应的角色数据
        sysRoleMapper.deleteByUserId(assginRoleDto.getUserId());
        //分配新的角色数据
        List<Long> roleIdList = assginRoleDto.getRoleIdList();
        roleIdList.forEach(roleId -> {
            sysRoleMapper.dodoAssign(assginRoleDto.getUserId(),roleId);
        });
    }
}
