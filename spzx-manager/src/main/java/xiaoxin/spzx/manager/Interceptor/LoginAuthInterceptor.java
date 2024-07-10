package xiaoxin.spzx.manager.Interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import xiaoxin.spzx.model.entity.system.SysUser;
import xiaoxin.spzx.model.vo.common.Result;
import xiaoxin.spzx.model.vo.common.ResultCodeEnum;
import xiaoxin.spzx.utils.AuthContextUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: LoginAuthInterceptor
 * Package: xiaoxin.spzx.manager.Interceptor
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/9 15:53
 * @Version 1.0
 */
@Component
public class LoginAuthInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求方式
        String method = request.getMethod();
        if("OPTIONS".equals(method)) {
            //跨域预检请求，直接放行
            return true;
        }
        //获取token
        String token = request.getHeader("token");
        if(StrUtil.isBlank(token)) {
            responseNoLoginInfo(response);
            return false;
        }
        //如果token不为空,那么此时验证token的合法性
        String sysUserInfoJson = redisTemplate.opsForValue().get("user:login" + token);
        if(StrUtil.isEmpty(sysUserInfoJson)) {
            responseNoLoginInfo(response);
            return false;
        }
        //将用户数据存储到ThreadLocal中
        SysUser sysUser = JSON.parseObject(sysUserInfoJson, SysUser.class);
        AuthContextUtil.set(sysUser);
        //重置redis中的用户数据的有效时间
        redisTemplate.expire("user:login:"+token,30, TimeUnit.MINUTES);
        //放行
        return true;
    }

    /**
     * 响应208状态码给前端
     * @param response
     */
    private void responseNoLoginInfo(HttpServletResponse response) {
        Result<Object> result = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(result));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(writer != null) {
                writer.close();
            }
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //移除threadLocal中的数据
        AuthContextUtil.remove();
    }
}
