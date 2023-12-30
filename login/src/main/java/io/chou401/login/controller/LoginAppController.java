package io.chou401.login.controller;

import io.chou401.common.constant.LoginConstant;
import io.chou401.common.vo.login.LoginAppVo;
import io.chou401.common.vo.login.LoginTokenVo;
import io.chou401.framework.annotation.Login;
import io.chou401.framework.response.ApiResult;
import io.chou401.framework.utils.CookieUtil;
import io.chou401.framework.utils.HttpServletRequestUtil;
import io.chou401.login.dto.AccountLoginAppDto;
import io.chou401.login.dto.LoginAppDto;
import io.chou401.login.service.LoginAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * {@code @author}  chou401
 * {@code @date} 2022/6/26
 **/
@Slf4j
@RestController
@Tag(name = "APP登录")
@RequestMapping("/app")
public class LoginAppController {

    @Autowired
    private LoginAppService loginAppService;

    /**
     * APP小程序登录
     *
     * @param loginAppDto
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    @Operation(summary = "APP小程序登录")
    public ApiResult<LoginTokenVo> login(@Valid @RequestBody LoginAppDto loginAppDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LoginTokenVo loginTokenVo = loginAppService.login(loginAppDto);
        // 输出token到cookie
        addCookie(loginTokenVo.getToken(), request, response);
        return ApiResult.success(loginTokenVo);
    }

    /**
     * APP账号密码登录
     *
     * @param loginAccountAppDto
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping("/accountLogin")
    @Operation(summary = "APP账号密码登录")
    public ApiResult<LoginTokenVo> accountLogin(@Valid @RequestBody AccountLoginAppDto loginAccountAppDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LoginTokenVo loginTokenVo = loginAppService.accountLogin(loginAccountAppDto);
        // 输出token到cookie
        addCookie(loginTokenVo.getToken(), request, response);
        return ApiResult.success(loginTokenVo);
    }

    /**
     * 获取APP登录用户信息
     *
     * @return
     * @throws Exception
     */
    @Login
    @PostMapping("/getLoginUserInfo")
    @Operation(summary = "获取APP登录用户信息")
    public ApiResult<LoginAppVo> getLoginUserInfo() throws Exception {
        LoginAppVo loginAppVo = loginAppService.getLoginUserInfo();
        return ApiResult.success(loginAppVo);
    }

    /**
     * APP退出
     *
     * @return
     * @throws Exception
     */
    @Login
    @PostMapping("/logout")
    @Operation(summary = "APP退出")
    public ApiResult<Boolean> logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        loginAppService.logout();
        // 从cookie中删除token
        CookieUtil.deleteCookie(LoginConstant.APP_COOKIE_TOKEN_NAME, request, response);
        return ApiResult.success();
    }

    /**
     * 输出token到cookie
     *
     * @param token
     * @param request
     * @param response
     */
    private void addCookie(String token, HttpServletRequest request, HttpServletResponse response) {
        boolean docRequest = HttpServletRequestUtil.isDocRequest(request);
        if (docRequest) {
            CookieUtil.addCookie(LoginConstant.APP_COOKIE_TOKEN_NAME, token, request, response);
        }
    }

}
