package xiaoxin.spzx.utils;

import xiaoxin.spzx.model.entity.system.SysUser;
import xiaoxin.spzx.model.entity.user.UserInfo;

/**
 * ClassName: AuthContextUtil
 * Package: xiaoxin.spzx.utils
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/9 15:50
 * @Version 1.0
 */
public class AuthContextUtil {
    //创建一个ThreadLocal对象
    private static final ThreadLocal<SysUser> threadLocal = new ThreadLocal<>();
    private static final ThreadLocal<UserInfo> userInfoThreadLocal = new ThreadLocal<>();
    //定义存储数据的静态方法
    public static void set(SysUser user) {
        threadLocal.set(user);
    }
    //定义获取数据的方法
    public static SysUser get() {
        return threadLocal.get();
    }
    //删除数据的方法
    public static void remove() {
        threadLocal.remove();
    }
    //定义存储数据的静态方法
    public static void setUserInfo(UserInfo userInfo) {
        userInfoThreadLocal.set(userInfo);
    }
    //定义获取数据的方法
    public static UserInfo getUserInfo() {
        return userInfoThreadLocal.get();
    }
    //删除数据的方法
    public static void removeUserInfo() {
        userInfoThreadLocal.remove();
    }
}
