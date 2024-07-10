package xiaoxin.spzx.common.log.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xiaoxin.spzx.common.log.annotation.Log;
import xiaoxin.spzx.common.log.service.AsyncOperLogService;
import xiaoxin.spzx.common.log.utils.LogUtil;
import xiaoxin.spzx.model.entity.system.SysOperLog;

/**
 * ClassName: LogAspect
 * Package: xiaoxin.spzx.common.log.aspect
 * Description: 环绕通知切面类定义
 *
 * @Author xiaoxin
 * @Create 2024/7/10 15:53
 * @Version 1.0
 */
@Aspect
@Component
@Slf4j
public class LogAspect {
    @Autowired
    private AsyncOperLogService asyncOperLogService;
    @Around(value = "@annotation(sysLog)")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint, Log sysLog){
      //构建前置参数
        SysOperLog sysOperLog = new SysOperLog();
        LogUtil.beforeHandleLog(sysLog,joinPoint,sysOperLog);

        Object proceed = null;

        try {
            //执行业务方法
            proceed = joinPoint.proceed();
            //构建响应结果参数
            LogUtil.afterHandleLog(sysLog,proceed,sysOperLog,0,null);
        } catch (Throwable e) {
            e.printStackTrace();
            LogUtil.afterHandleLog(sysLog,proceed,sysOperLog,1,e.getMessage());
            //抛出异常防止---事务失效
            throw new RuntimeException();
        }
        //保存日志数据
        asyncOperLogService.saveSysOperLog(sysOperLog);
        //返回执行结果
        return proceed;
    }
}
