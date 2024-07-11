package xiaoxin.spzx.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xiaoxin.spzx.model.entity.user.UserAddress;
import xiaoxin.spzx.model.vo.common.Result;
import xiaoxin.spzx.model.vo.common.ResultCodeEnum;
import xiaoxin.spzx.user.service.UserAddressService;

import java.util.List;

/**
 * ClassName: UserAddressController
 * Package: xiaoxin.spzx.user.controller
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 10:04
 * @Version 1.0
 */
@Tag(name = "用户地址接口")
@RestController
@RequestMapping(value = "/api/user/userAddress")
@SuppressWarnings({"unchecked","rawtypes"})
public class UserAddressController {
    @Autowired
    private UserAddressService userAddressService;

    @Operation(summary = "获取用户地址列表")
    @GetMapping("auth/findUserAddressList")
    public Result<List<UserAddress>> findUserAddressList() {
        List<UserAddress> list = userAddressService.findUserAddressList();
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "获取地址信息")
    @GetMapping("/getUserAddress/{id}")
    public UserAddress getUserAddress(@PathVariable Long id) {
        return userAddressService.getById(id);
    }

}
