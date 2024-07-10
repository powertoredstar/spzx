package xiaoxin.spzx.manager.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import xiaoxin.spzx.common.log.service.AsyncOperLogService;
import xiaoxin.spzx.manager.mapper.SysOperLogMapper;
import xiaoxin.spzx.model.entity.system.SysOperLog;

/**
 * ClassName: AsyncOperLogServiceImpl
 * Package: xiaoxin.spzx.common.log.service.Impl
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 16:19
 * @Version 1.0
 */
@Service
public class AsyncOperLogServiceImpl implements AsyncOperLogService {
    @Autowired
    private SysOperLogMapper sysOperLogMapper;
    @Async//异步执行保存日志操作
    @Override
    public void saveSysOperLog(SysOperLog sysOperLog) {
        sysOperLogMapper.insert(sysOperLog);
    }
}
