package xiaoxin.spzx.user.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xiaoxin.spzx.model.entity.user.UserAddress;
import xiaoxin.spzx.user.mapper.UserAddressMapper;
import xiaoxin.spzx.user.service.UserAddressService;
import xiaoxin.spzx.utils.AuthContextUtil;

import java.util.List;

/**
 * ClassName: UserAddressServiceImpl
 * Package: xiaoxin.spzx.user.service.Impl
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 10:06
 * @Version 1.0
 */
@Service
public class UserAddressServiceImpl implements UserAddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;
    @Override
    public List<UserAddress> findUserAddressList() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        return userAddressMapper.findByUserId(userId);
    }

    @Override
    public UserAddress getById(Long id) {
       return userAddressMapper.getById();
    }
}
