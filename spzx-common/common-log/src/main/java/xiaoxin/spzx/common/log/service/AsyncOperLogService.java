package xiaoxin.spzx.common.log.service;

import xiaoxin.spzx.model.entity.system.SysOperLog;

/**
 * ClassName: AsyncOperLogService
 * Package: xiaoxin.spzx.common.log.service
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 16:19
 * @Version 1.0
 */
public interface AsyncOperLogService {
    void saveSysOperLog(SysOperLog sysOperLog);
}
