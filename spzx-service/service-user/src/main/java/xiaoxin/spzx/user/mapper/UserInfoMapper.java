package xiaoxin.spzx.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import xiaoxin.spzx.model.entity.user.UserInfo;

/**
 * ClassName: UserInfoMapper
 * Package: xiaoxin.spzx.user.mapper
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 22:20
 * @Version 1.0
 */
@Mapper
public interface UserInfoMapper {
    UserInfo getByUsername(String username);

    void save(UserInfo userInfo);
}
