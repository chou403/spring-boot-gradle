package io.chou401.framework.interceptor.login;

import io.chou401.common.cache.TokenCache;
import io.chou401.framework.interceptor.BaseExcludeMethodInterceptor;
import io.chou401.framework.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Token拦截器
 *
 * {@code @author}  chou401
 * {@code @date} 2023/12/03
 **/
@Slf4j
public class TokenInterceptor extends BaseExcludeMethodInterceptor {

    @Override
    protected boolean preHandleMethod(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
        // 获取token
        String token = TokenUtil.getToken(request);
        if (StringUtils.isBlank(token)) {
            return true;
        }
        // 设置token值到当前线程中，避免重复获取
        TokenCache.set(token);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        TokenCache.remove();
    }

}
