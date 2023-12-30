package io.chou401.login.service.impl;

import io.chou401.common.constant.SysDictConstant;
import io.chou401.common.entity.user.User;
import io.chou401.common.enums.SystemType;
import io.chou401.common.vo.login.LoginAppVo;
import io.chou401.common.vo.login.LoginTokenVo;
import io.chou401.framework.exception.BusinessException;
import io.chou401.framework.exception.LoginException;
import io.chou401.framework.service.LoginRedisAppService;
import io.chou401.framework.utils.IpRegionUtil;
import io.chou401.framework.utils.IpUtil;
import io.chou401.framework.utils.PasswordUtil;
import io.chou401.framework.utils.TokenUtil;
import io.chou401.framework.utils.api.WxMpApi;
import io.chou401.framework.utils.login.LoginAppUtil;
import io.chou401.login.api.SystemApi;
import io.chou401.login.api.UserApi;
import io.chou401.login.dto.AccountLoginAppDto;
import io.chou401.login.dto.LoginAppDto;
import io.chou401.login.service.LoginAppService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * {@code @author}  chou401
 * {@code @date} 2022/7/12
 **/
@Slf4j
@Service
public class LoginAppServiceImpl implements LoginAppService {

    @Autowired
    private UserApi userApi;

    @Autowired
    private SystemApi systemApi;

    @Autowired
    private LoginRedisAppService loginRedisAppService;

    @Override
    public LoginTokenVo login(LoginAppDto dto) throws Exception {
        String code = dto.getCode();
        // 获取微信openid
        String openid = WxMpApi.getOpenid(code);
        // 根据openid获取用户信息
        User user = userApi.getUserByOpenid(openid);
        // 判断是否存在，不存在，则添加
        if (user == null) {
            // 注册
            user = new User();
            // 用户昵称：用户+8为随机数字
            user.setNickname("用户" + RandomStringUtils.randomNumeric(8));
            // 设置微信小程序openid
            user.setOpenid(openid);
            // 设置注册时间
            user.setRegisterTime(new Date());
            String requestIp = IpUtil.getRequestIp();
            String ipAreaDesc = IpRegionUtil.getIpAreaDesc(requestIp);
            // 设置注册IP
            user.setRegisterIp(requestIp);
            // 设置注册IP区域
            user.setRegisterIpArea(ipAreaDesc);
            boolean flag = userApi.save(user);
            if (!flag) {
                throw new Exception("注册异常");
            }
            // 获取用户ID
            Long userId = user.getId();
            // 根据用户ID获取用户下信息
            user = userApi.getById(userId);
        }
        return login(user);
    }

    @Override
    public LoginTokenVo accountLogin(AccountLoginAppDto dto) throws Exception {
        String username = dto.getUsername();
        User user = userApi.getUserByUsername(username);
        // 校验密码
        String dbPassword = user.getPassword();
        String dbSalt = user.getSalt();
        String password = dto.getPassword();
        String encryptPassword = PasswordUtil.encrypt(password, dbSalt);
        if (!encryptPassword.equals(dbPassword)) {
            throw new BusinessException("账号密码错误");
        }
        return login(user);
    }

    @Override
    public LoginTokenVo login(User user) throws Exception {
        if (user == null) {
            throw new LoginException("用户信息不存在");
        }
        Long userId = user.getId();
        // 生成token
        String token = TokenUtil.generateAppToken(userId);
        // 最后一次登录时间
        Date lastLoginTime = new Date();
        // 刷新登录信息
        refreshLoginInfo(user, token, lastLoginTime);
        // 设置登录信息
        String requestIp = IpUtil.getRequestIp();
        String ipAreaDesc = IpRegionUtil.getIpAreaDesc(requestIp);
        // 设置最后登录时间
        user.setLastLoginTime(lastLoginTime);
        // 设置最后登录IP
        user.setLastLoginIp(requestIp);
        // 设置最后登录IP区域
        user.setLastLoginIpArea(ipAreaDesc);
        userApi.updateById(user);

        // TODO 添加用户登录日志

        // 返回token
        LoginTokenVo loginTokenVo = new LoginTokenVo();
        loginTokenVo.setToken(token);
        return loginTokenVo;
    }

    @Override
    public LoginAppVo refreshLoginInfo(User user, String token, Date lastLoginTime) throws Exception {
        Long userId = user.getId();
        // 校验用户状态
        Boolean status = user.getStatus();
        if (status == false) {
            throw new LoginException("用户已禁用");
        }
        // 设置登录用户对象
        LoginAppVo loginAppVo = new LoginAppVo();
        BeanUtils.copyProperties(user, loginAppVo);
        loginAppVo.setUserId(userId);
        loginAppVo.setLastLoginTime(lastLoginTime);
        // 系统类型 1：管理后台，2：用户端
        loginAppVo.setSystemType(SystemType.APP.getCode());
        loginAppVo.setVip(user.getIsVip());
        Integer vipLevel = user.getVipLevel();
        if (vipLevel != null) {
            String vipName = systemApi.getSysDictLabelByValue(SysDictConstant.VIP_LEVEL, vipLevel);
            loginAppVo.setVipName(vipName);
        }
        // 保存登录信息到redis中
        loginRedisAppService.setLoginVo(token, loginAppVo);
        return loginAppVo;
    }

    @Override
    public LoginAppVo getLoginUserInfo() throws Exception {
        LoginAppVo loginAppVo = LoginAppUtil.getLoginVo();
        if (loginAppVo == null) {
            throw new LoginException("请先登录");
        }
        Long userId = loginAppVo.getUserId();
        User user = userApi.getById(userId);
        // 刷新用户登录信息
        String token = TokenUtil.getToken();
        Date lastLoginTime = loginAppVo.getLastLoginTime();
        loginAppVo = refreshLoginInfo(user, token, lastLoginTime);
        return loginAppVo;
    }

    @Override
    public void logout() throws Exception {
        // 获取token
        String token = TokenUtil.getToken();
        // 删除缓存
        loginRedisAppService.deleteLoginVo(token);
    }
}
