package io.chou401.login.service;

import io.chou401.common.entity.sys.SysUser;
import io.chou401.common.vo.login.LoginTokenVo;
import io.chou401.common.vo.login.LoginVo;
import io.chou401.login.dto.LoginDto;

import java.util.Date;

/**
 * {@code @author}  chou401
 * {@code @date} 2022/7/5
 **/
public interface LoginService {

    /**
     * 登录
     *
     * @param dto
     * @return
     * @throws Exception
     */
    LoginTokenVo login(LoginDto dto) throws Exception;

    /**
     * 处理登录用户信息
     *
     * @param sysUser
     * @param token
     * @param loginTime
     * @return
     * @throws Exception
     */
    LoginVo refreshLoginInfo(SysUser sysUser, String token, Date loginTime) throws Exception;

    /**
     * 获取登录用户信息
     *
     * @return
     * @throws Exception
     */
    LoginVo getLoginUserInfo() throws Exception;

    /**
     * 登出
     *
     * @throws Exception
     */
    void logout() throws Exception;

}
