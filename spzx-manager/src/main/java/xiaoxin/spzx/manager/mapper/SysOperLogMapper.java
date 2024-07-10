package xiaoxin.spzx.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import xiaoxin.spzx.model.entity.system.SysOperLog;

/**
 * ClassName: SysOperLogMapper
 * Package: xiaoxin.spzx.manager.mapper
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 16:30
 * @Version 1.0
 */
@Mapper
public interface SysOperLogMapper {
    void insert(SysOperLog sysOperLog);
}
