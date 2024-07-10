package xiaoxin.spzx.manager.service.Impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import xiaoxin.exception.XiaoxinException;
import xiaoxin.spzx.manager.mapper.SysUserMapper;
import xiaoxin.spzx.manager.service.SysUserService;
import xiaoxin.spzx.model.dto.system.LoginDto;
import xiaoxin.spzx.model.dto.system.SysUserDto;
import xiaoxin.spzx.model.entity.system.SysUser;
import xiaoxin.spzx.model.vo.common.ResultCodeEnum;
import xiaoxin.spzx.model.vo.system.LoginVo;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: SysUserServiceImpl
 * Package: xiaoxin.spzx.service.Impl
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/9 14:22
 * @Version 1.0
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public PageInfo<SysUser> findByPage(SysUserDto sysUserDto, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysUser> sysUserList = sysUserMapper.findByPage(sysUserDto);
        PageInfo<SysUser> pageInfo = new PageInfo<>(sysUserList);
        return pageInfo;
    }

    @Override
    public LoginVo login(LoginDto loginDto) {
        //校验验证码是否正确
        //用户输入的验证码
        String captcha = loginDto.getCaptcha();
        //redis中验证码的数据key
        String codeKey = loginDto.getCodeKey();
        //从redis中获取验证码
        String redisCode = redisTemplate.opsForValue().get("user:login:validatecode:" + codeKey);
        if(StringUtils.isEmpty(redisCode) || !redisCode.equalsIgnoreCase(captcha)) {
            throw new XiaoxinException(ResultCodeEnum.VALIDATECODE_ERROR);
        }
        //验证通过删除热redis中的验证码
        redisTemplate.delete("user:login:validatecode:" + codeKey);
        //1.根据用户名查询用户
        SysUser sysUser = sysUserMapper.selectByUserName(loginDto.getUserName());
        if(sysUser == null){
            throw new XiaoxinException(ResultCodeEnum.LOGIN_ERROR);
        }
        //2.验证密码是否正确
        String inputPassword = loginDto.getPassword();
        String md5InputPassword = DigestUtils.md5DigestAsHex(inputPassword.getBytes());
        if(!md5InputPassword.equals(sysUser.getPassword())){
            throw new XiaoxinException(ResultCodeEnum.LOGIN_ERROR);
        }
        //3.生成令牌,保存数据到redis中
        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set("user：login"+token, JSON.toJSONString(sysUser),30, TimeUnit.MINUTES);
        //构建响应结果对象
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        loginVo.setRefresh_token("");
        //返回
        return loginVo;
    }

    @Override
    public SysUser getUserInfo(String token) {
        String userJson = redisTemplate.opsForValue().get("user:login:" + token);
        return JSON.parseObject(userJson, SysUser.class);
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete("user:login:" + token);
    }

    @Override
    public void saveSysUser(SysUser sysUser) {
        //根据输入的用户名查询用户
        SysUser dbSysUser = sysUserMapper.findByUserName(sysUser.getUserName());
        if(dbSysUser != null){
            throw new XiaoxinException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }
        //对密码进行加密
        String password = sysUser.getPassword();
        String digestPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        sysUser.setPassword(digestPassword);
        sysUser.setStatus(0);
        sysUserMapper.saveSysUser(sysUser);
    }

    @Override
    public void updateSysUser(SysUser sysUser) {
        sysUserMapper.updateSysUser(sysUser);
    }

    @Override
    public void deleteById(Integer userId) {
        sysUserMapper.deleteById(userId);
    }
}
