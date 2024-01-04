package io.chou401.user.controller;

import io.chou401.common.entity.user.User;
import io.chou401.common.enums.SysLogType;
import io.chou401.common.vo.user.UserVo;
import io.chou401.framework.annotation.Log;
import io.chou401.framework.annotation.Permission;
import io.chou401.framework.response.ApiResult;
import io.chou401.user.dto.UserDto;
import io.chou401.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/12/27
 * {@code @description} 内部服务调用 api
 */
@Slf4j
@RestController
@Tag(name = "内部服务调用")
@RequestMapping("/admin/user/feign")
public class UserApiController {

    @Autowired
    private UserService userService;

    /**
     * 根据微信openid获取用户
     *
     * @param openid
     * @return
     * @throws Exception
     */
    @PostMapping("/getUserByOpenid")
    @Operation(summary = "根据微信openid获取用户")
    public ApiResult<User> getUserByOpenid(@RequestBody String openid) throws Exception {
        User user = userService.getUserByOpenid(openid);
        return ApiResult.success(user);
    }

    /**
     * 添加用户信息
     *
     * @param userDto
     * @return
     * @throws Exception
     */
    @Log(value = "添加用户信息", type = SysLogType.ADD)
    @PostMapping("/addUser")
    @Operation(summary = "添加用户信息")
    @Permission("user:add")
    public ApiResult addUser(@Valid @RequestBody UserDto userDto) throws Exception {
        boolean flag = userService.addUser(userDto);
        return ApiResult.result(flag);
    }

    /**
     * 获取用户信息详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/getUser")
    @Operation(summary = "用户信息详情")
    @Permission("user:info")
    public ApiResult<UserVo> getUser(@RequestBody Long id) throws Exception {
        UserVo userVo = userService.getUserById(id);
        return ApiResult.success(userVo);
    }

    /**
     * 根据账号获取用户
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/getUserByUsername")
    @Operation(summary = "根据账号获取用户")
    @Permission("user:info")
    public ApiResult<User> getUserByUsername(@RequestBody String username) throws Exception {
        User user = userService.getUserByUsername(username);
        return ApiResult.success(user);
    }
}
