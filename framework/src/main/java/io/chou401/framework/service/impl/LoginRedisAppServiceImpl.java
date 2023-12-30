package io.chou401.framework.service.impl;

import io.chou401.common.constant.RedisKey;
import io.chou401.common.vo.login.LoginAppVo;
import io.chou401.framework.config.properties.LoginAppProperties;
import io.chou401.framework.exception.LoginException;
import io.chou401.framework.exception.LoginTokenException;
import io.chou401.framework.service.LoginRedisAppService;
import io.chou401.framework.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * {@code @author}  chou401
 * {@code @date} 2022/7/12
 **/
@Slf4j
@Service
public class LoginRedisAppServiceImpl implements LoginRedisAppService {

    private static final TimeUnit TOKEN_TIME_UNIT = TimeUnit.DAYS;

    @Autowired
    private LoginAppProperties loginAppProperties;

    @Autowired
    private RedisTemplate redisTemplate;

    private Integer tokenExpireDays;

    @PostConstruct
    public void init() {
        log.info("loginAppProperties = " + loginAppProperties);
        tokenExpireDays = loginAppProperties.getTokenExpireDays();
    }

    @Override
    public String getLoginRedisKey(String token) throws Exception {
        String loginRedisKey = String.format(RedisKey.LOGIN_TOKEN, token);
        return loginRedisKey;
    }

    @Override
    public void setLoginVo(String token, LoginAppVo loginAppVo) throws Exception {
        if (loginAppVo == null) {
            throw new LoginException("登录用户信息不能为空");
        }
        if (loginAppProperties.isSingleLogin()) {
            // 单点登录，则删除之前该用户的key
            deleteLoginInfoByToken(token);
        }
        // 用户信息
        String loginTokenRedisKey = getLoginRedisKey(token);
        redisTemplate.opsForValue().set(loginTokenRedisKey, loginAppVo, tokenExpireDays, TOKEN_TIME_UNIT);
    }

    @Override
    public LoginAppVo getLoginVo(String token) throws Exception {
        if (StringUtils.isBlank(token)) {
            throw new LoginTokenException("token不能为空");
        }
        String loginRedisKey = getLoginRedisKey(token);
        LoginAppVo loginAppVo = (LoginAppVo) redisTemplate.opsForValue().get(loginRedisKey);
        return loginAppVo;
    }

    @Override
    public void deleteLoginVo(String token) throws Exception {
        if (StringUtils.isBlank(token)) {
            throw new LoginTokenException("token不能为空");
        }
        String loginTokenRedisKey = getLoginRedisKey(token);
        redisTemplate.delete(loginTokenRedisKey);
    }

    @Override
    public void refreshToken() throws Exception {
        // 刷新token
        String token = TokenUtil.getToken();
        if (StringUtils.isBlank(token)) {
            return;
        }
        // 刷新key的过期时间
        String loginTokenRedisKey = getLoginRedisKey(token);
        redisTemplate.expire(loginTokenRedisKey, tokenExpireDays, TOKEN_TIME_UNIT);
    }

    @Override
    public void deleteLoginInfoByToken(String token) throws Exception {
        log.info("清除用户的所有redis登录信息：" + token);
        if (StringUtils.isBlank(token)) {
            throw new LoginTokenException("token不能为空");
        }
        int lastIndexOf = token.lastIndexOf(".");
        String userTokenPrefix = token.substring(0, lastIndexOf + 1);
        // 删除之前该用户的key
        String userTokenRedisPrefix = userTokenPrefix + "*";
        redisTemplate.delete(userTokenRedisPrefix);
    }

}
