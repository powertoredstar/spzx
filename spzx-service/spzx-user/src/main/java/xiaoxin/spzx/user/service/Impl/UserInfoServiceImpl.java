package xiaoxin.spzx.user.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xiaoxin.spzx.model.dto.h5.UserRegisterDto;
import xiaoxin.spzx.user.mapper.UserInfoMapper;
import xiaoxin.spzx.user.service.UserInfoService;

/**
 * ClassName: UserInfoServiceImpl
 * Package: xiaoxin.spzx.user.service.Impl
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 22:20
 * @Version 1.0
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(UserRegisterDto userRegisterDto) {

    }
}
