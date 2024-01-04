package io.chou401.login.controller;

import io.chou401.common.constant.LoginConstant;
import io.chou401.common.vo.login.LoginTokenVo;
import io.chou401.common.vo.login.LoginVo;
import io.chou401.framework.response.ApiResult;
import io.chou401.framework.utils.CookieUtil;
import io.chou401.login.dto.LoginDto;
import io.chou401.login.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

/**
 * {@code @author}  chou401
 * {@code @date} 2022/6/26
 **/
@Slf4j
@RestController
@Tag(name = "管理后台登录")
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 管理后台登录
     *
     * @param loginDto
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    @Operation(summary = "管理后台登录")
    public ApiResult<LoginTokenVo> login(@Valid @RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LoginTokenVo loginTokenVo = loginService.login(loginDto);
        // 输出token到cookie
        CookieUtil.addCookie(LoginConstant.ADMIN_COOKIE_TOKEN_NAME, loginTokenVo.getToken(), request, response);
        return ApiResult.success(loginTokenVo);
    }

    /**
     * 获取管理后台登录用户信息
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/getLoginUserInfo")
    @Operation(summary = "获取管理后台登录用户信息")
    public ApiResult<LoginVo> getLoginUserInfo() throws Exception {
        LoginVo loginVo = loginService.getLoginUserInfo();
        return ApiResult.success(loginVo);
    }

    /**
     * 管理后台退出
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/logout")
    @Operation(summary = "管理后台退出")
    public ApiResult<Boolean> logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 删除缓存
        loginService.logout();
        // 从cookie中删除token
        CookieUtil.deleteCookie(LoginConstant.ADMIN_COOKIE_TOKEN_NAME, request, response);
        return ApiResult.success();
    }

}
