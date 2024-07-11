package xiaoxin.spzx.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import xiaoxin.spzx.model.entity.user.UserAddress;

import java.util.List;

/**
 * ClassName: UserAddressMapper
 * Package: xiaoxin.spzx.user.mapper
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 10:06
 * @Version 1.0
 */
@Mapper
public interface UserAddressMapper {

    List<UserAddress> findByUserId(Long userId);

    UserAddress getById();
}
