package io.chou401.framework.interceptor.login;

import io.chou401.framework.exception.NotAuthException;
import io.chou401.framework.interceptor.BaseExcludeMethodInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;

/**
 * 没权限拦截器，项目演示用，生成项目根据情况而定
 * {@code @author}  chou401
 * {@code @date} 2022/6/26
 **/
@Slf4j
public class NotAuthInterceptor extends BaseExcludeMethodInterceptor {

    @Override
    protected boolean preHandleMethod(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
        log.info("演示环境没有权限访问的url：" + request.getServletPath());
        throw new NotAuthException("演示环境不允许此操作");
    }

}
