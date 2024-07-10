package xiaoxin.spzx.manager.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xiaoxin.spzx.manager.mapper.SysRoleMenuMapper;
import xiaoxin.spzx.manager.service.SysMenuService;
import xiaoxin.spzx.manager.service.SysRoleMenuService;
import xiaoxin.spzx.model.dto.system.AssginMenuDto;
import xiaoxin.spzx.model.entity.system.SysMenu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: SysRoleMenuServicImpl
 * Package: xiaoxin.spzx.manager.service.Impl
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/9 21:16
 * @Version 1.0
 */
@Service
public class SysRoleMenuServicImpl implements SysRoleMenuService {
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private SysMenuService sysMenuService;
    @Override
    public Map<String, Object> findSysRoleMenuByRoleId(Long roleId) {
        //查询所有的菜单数据
        List<SysMenu> sysMenuList = sysMenuService.findNodes();
        //查询当前角色的菜单数据
        List<Long> roleMenuIds = sysRoleMenuMapper.findSysRoleMenuByRoleId(roleId);
        //将数据存储到map中进行返回
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sysMenuList", sysMenuList);
        map.put("roleMenuIds", roleMenuIds);
        //返回
        return map;
    }

    @Override
    public void doAssign(AssginMenuDto assginMenuDto) {
        //根据角色的id删除其对应的菜单数据
        sysRoleMenuMapper.deleteByRoleId(assginMenuDto.getRoleId());
        //获取菜单的id
        List<Map<String,Number>> menuInfo = assginMenuDto.getMenuIdList();
        if(menuInfo != null && menuInfo.size() > 0){
            sysRoleMenuMapper.doAssign(assginMenuDto);
        }
    }
}
