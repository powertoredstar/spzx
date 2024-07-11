package xiaoxin.spzx.user.service;

import xiaoxin.spzx.model.entity.user.UserAddress;

import java.util.List;

/**
 * ClassName: UserAddressService
 * Package: xiaoxin.spzx.user.service
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 10:06
 * @Version 1.0
 */
public interface UserAddressService {
    List<UserAddress> findUserAddressList();

    UserAddress getById(Long id);
}
