package xiaoxin.spzx.user.service;

import xiaoxin.spzx.model.dto.h5.UserLoginDto;
import xiaoxin.spzx.model.dto.h5.UserRegisterDto;
import xiaoxin.spzx.model.vo.h5.UserInfoVo;

/**
 * ClassName: UserInfoService
 * Package: xiaoxin.spzx.user.service
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 22:19
 * @Version 1.0
 */
public interface UserInfoService {
    void register(UserRegisterDto userRegisterDto);

    String login(UserLoginDto userLoginDto);

    UserInfoVo getCurrentUserInfo(String token);
}
