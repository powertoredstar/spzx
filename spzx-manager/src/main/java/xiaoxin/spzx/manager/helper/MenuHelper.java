package xiaoxin.spzx.manager.helper;

import xiaoxin.spzx.model.entity.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: MenuHelper
 * Package: xiaoxin.spzx.manager.helper
 * Description:
 *              构建树形菜单的工具类
 * @Author xiaoxin
 * @Create 2024/7/9 20:27
 * @Version 1.0
 */
public class MenuHelper {
    /**
     * 使用递归方法建菜单
     * @param sysMenusList
     * @return
     */
    public static List<SysMenu> buildTree(List<SysMenu> sysMenusList) {
        List<SysMenu> trees = new ArrayList<SysMenu>();
        for (SysMenu sysMenu : sysMenusList) {
            if(sysMenu.getParentId().longValue() == 0) {
                trees.add(findChildren(sysMenu,sysMenusList));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param sysMenu
     * @param treeNodes
     * @return
     */
    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> treeNodes) {
        sysMenu.setChildren(new ArrayList<SysMenu>());
        for (SysMenu it : treeNodes) {
            if(sysMenu.getId().longValue() == it.getParentId().longValue()) {
                sysMenu.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return sysMenu;
    }
}
