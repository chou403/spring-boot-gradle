package io.chou401.framework.interceptor.login;

import io.chou401.common.cache.LoginAppCache;
import io.chou401.common.cache.LoginCache;
import io.chou401.common.enums.SystemType;
import io.chou401.common.vo.login.LoginAppVo;
import io.chou401.common.vo.login.LoginVo;
import io.chou401.framework.exception.LoginTokenException;
import io.chou401.framework.interceptor.BaseExcludeMethodInterceptor;
import io.chou401.framework.utils.SystemTypeUtil;
import io.chou401.framework.utils.TokenUtil;
import io.chou401.framework.utils.login.LoginAppUtil;
import io.chou401.framework.utils.login.LoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * {@code @author}  chou401
 * {@code @date} 2022/6/26
 **/
@Slf4j
public class LoginCommonInterceptor extends BaseExcludeMethodInterceptor {

    @Override
    protected boolean preHandleMethod(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
        // 获取token
        String token = TokenUtil.getToken();
        SystemType systemType = null;
        if (StringUtils.isNotBlank(token)) {
            systemType = SystemTypeUtil.getSystemTypeByToken(token);
            if (SystemType.ADMIN == systemType) {
                // 获取管理后台登录用户信息
                LoginVo loginVo = LoginUtil.getLoginVo(token);
                if (loginVo != null) {
                    // 将管理后台的登录信息保存到当前线程中
                    LoginCache.set(loginVo);
                }
            } else if (SystemType.APP == systemType) {
                LoginAppVo loginVo = LoginAppUtil.getLoginVo(token);
                if (loginVo != null) {
                    // 将APP移动端的登录信息保存到当前线程中
                    LoginAppCache.set(loginVo);
                }
            }
        }
        // 如果不存在@Login注解，则跳过
        boolean existLoginAnnotation = isExistLoginAnnotation(handlerMethod);
        if (!existLoginAnnotation) {
            return true;
        }
        if (StringUtils.isBlank(token)) {
            throw new LoginTokenException("请登录后再操作");
        }
        if (SystemType.ADMIN == systemType) {
            // 获取管理后台登录用户信息
            LoginVo loginVo = LoginCache.get();
            if (loginVo == null) {
                throw new LoginTokenException("登录已过期或登录信息不存在，请重新登录");
            }
        } else if (SystemType.APP == systemType) {
            LoginAppVo loginVo = LoginAppCache.get();
            if (loginVo == null) {
                throw new LoginTokenException("登录已过期或登录信息不存在，请重新登录");
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoginCache.remove();
        LoginAppCache.remove();
    }
}
