package xiaoxin.spzx.user.service.Impl;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import xiaoxin.exception.XiaoxinException;
import xiaoxin.spzx.model.dto.h5.UserLoginDto;
import xiaoxin.spzx.model.dto.h5.UserRegisterDto;
import xiaoxin.spzx.model.entity.user.UserInfo;
import xiaoxin.spzx.model.vo.common.ResultCodeEnum;
import xiaoxin.spzx.model.vo.h5.UserInfoVo;
import xiaoxin.spzx.user.mapper.UserInfoMapper;
import xiaoxin.spzx.user.service.UserInfoService;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
        //获取数据
        String username = userRegisterDto.getUsername();
        String password = userRegisterDto.getPassword();
        String nickName = userRegisterDto.getNickName();
        String code = userRegisterDto.getCode();
        //校验参数
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(nickName) || StringUtils.isEmpty(code)){
            throw new XiaoxinException(ResultCodeEnum.DATA_ERROR);
        }

        //校验验证码
        String codeValueRedis = redisTemplate.opsForValue().get("phone:code" + username);
        if(StringUtils.isEmpty(codeValueRedis) || !codeValueRedis.equals(code)){
            throw new XiaoxinException(ResultCodeEnum.VALIDATECODE_ERROR);
        }
       UserInfo userInfo = userInfoMapper.getByUsername(username);
        if(userInfo != null){
            throw new XiaoxinException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }
        //保存用户信息
         userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        userInfo.setNickName(nickName);
        userInfo.setPhone(username);
        userInfo.setStatus(1);
        userInfo.setSex(0);
        userInfo.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        userInfoMapper.save(userInfo);
        //删除缓存
        redisTemplate.delete("phone:code" + username);
    }

    @Override
    public String login(UserLoginDto userLoginDto) {
        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();

        //校验参数
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            throw new XiaoxinException(ResultCodeEnum.DATA_ERROR);
        }
        UserInfo userInfo = userInfoMapper.getByUsername(username);
        if(userInfo == null){
            throw new XiaoxinException(ResultCodeEnum.LOGIN_ERROR);
        }
        //校验密码
        if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(userInfo.getPassword())){
            throw new XiaoxinException(ResultCodeEnum.LOGIN_ERROR);
        };

        //校验是否被禁用
        if(userInfo.getStatus() == 0){
            throw new XiaoxinException(ResultCodeEnum.ACCOUNT_STOP);
        }
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        redisTemplate.opsForValue().set("user:spzx:"+ token, JSON.toJSONString(userInfo),30, TimeUnit.DAYS);
        return token;
    }

    @Override
    public UserInfoVo getCurrentUserInfo(String token) {
        String userInfoJSON = redisTemplate.opsForValue().get("user:spzx:" + token);
        if(StringUtils.isEmpty(userInfoJSON)){
            throw new XiaoxinException(ResultCodeEnum.LOGIN_AUTH);
        }
        UserInfo userInfo = JSON.parseObject(userInfoJSON, UserInfo.class);
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(userInfo, userInfoVo);
        return userInfoVo;

    }
}
