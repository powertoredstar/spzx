package xiaoxin.spzx.manager.service.Impl;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import xiaoxin.exception.XiaoxinException;
import xiaoxin.spzx.manager.helper.MenuHelper;
import xiaoxin.spzx.manager.mapper.SysMenuMapper;
import xiaoxin.spzx.manager.mapper.SysRoleMenuMapper;
import xiaoxin.spzx.manager.service.SysMenuService;
import xiaoxin.spzx.model.entity.system.SysMenu;
import xiaoxin.spzx.model.entity.system.SysUser;
import xiaoxin.spzx.model.vo.common.ResultCodeEnum;
import xiaoxin.spzx.model.vo.system.SysMenuVo;
import xiaoxin.spzx.utils.AuthContextUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * ClassName: SysMenuServiceImpl
 * Package: xiaoxin.spzx.manager.service.Impl
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/9 20:20
 * @Version 1.0
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysMenu> findNodes() {
        List<SysMenu> sysMenuList = sysMenuMapper.selectAll();
        if(CollectionUtils.isEmpty(sysMenuList)){
            return null;
        }
        //构建树形数据
        List<SysMenu> treeMenuList = MenuHelper.buildTree(sysMenuList);
        return treeMenuList;
    }
    @Transactional
    @Override
    public void save(SysMenu sysMenu) {
        //添加新的节点
        sysMenuMapper.insert(sysMenu);
        //新添加一个菜单,那么此时就需要将该菜单所对应的父级菜单设置为半开
        updateSysRoleMenuIsHalf(sysMenu);
    }

    private void updateSysRoleMenuIsHalf(SysMenu sysMenu) {
        //查询是否存在父节点
       SysMenu parentMenu =  sysMenuMapper.selectById(sysMenu.getParentId());
       if(parentMenu != null){
           //将该id的菜单设置为半开
           sysRoleMenuMapper.updateSysRoleMenuIsHalf(parentMenu.getId());
           //递归调用
           updateSysRoleMenuIsHalf(parentMenu);
       }
    }

    @Override
    public void updateById(SysMenu sysMenu) {
        sysMenuMapper.updateById(sysMenu);
    }

    @Override
    public void removeById(Long id) {
        //先查询是否存在子菜单,如果存在不允许进行删除
       int count = sysMenuMapper.countByParentId(id);
       if(count > 0){
           throw new XiaoxinException(ResultCodeEnum.NODE_ERROR);
       }
        //不存在子菜单直接删除
        sysMenuMapper.deleteById(id);
    }

    @Override
    public List<SysMenuVo> findUserMenuList() {
        //获取当前登录用户的id
        SysUser sysUser = AuthContextUtil.get();
        Long userId = sysUser.getId();
       List<SysMenu> sysMenuList = sysMenuMapper.selectListByUserId();
        //构建树形数据
        List<SysMenu> sysMenuTreeList = MenuHelper.buildTree(sysMenuList);
        return this.buildMenus(sysMenuTreeList);

    }
    //将List<SysMenu>对象转化成List<SysMenuVo>对象
    private List<SysMenuVo> buildMenus(List<SysMenu> sysMenuTreeList) {
        LinkedList<SysMenuVo> list = new LinkedList<>();
        for(SysMenu sysMenu : sysMenuTreeList){
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setTitle(sysMenu.getTitle());
            sysMenuVo.setName(sysMenu.getComponent());
            List<SysMenu> children = sysMenu.getChildren();
            if(!CollectionUtils.isEmpty(children)){
                sysMenuVo.setChildren(buildMenus(children));
            }
            list.add(sysMenuVo);
        }
        return list;
    }
}
