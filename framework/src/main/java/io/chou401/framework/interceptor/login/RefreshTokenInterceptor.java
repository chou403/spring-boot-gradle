package io.chou401.framework.interceptor.login;

import io.chou401.common.enums.SystemType;
import io.chou401.framework.interceptor.BaseExcludeMethodInterceptor;
import io.chou401.framework.service.LoginRedisAppService;
import io.chou401.framework.service.LoginRedisService;
import io.chou401.framework.utils.SystemTypeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {@code @author}  chou401
 * {@code @date} 2022/6/26
 **/
@Slf4j
public class RefreshTokenInterceptor extends BaseExcludeMethodInterceptor {

    @Autowired
    private LoginRedisService loginRedisService;

    @Autowired
    private LoginRedisAppService loginRedisAppService;

    @Override
    protected boolean preHandleMethod(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
        SystemType systemType = SystemTypeUtil.getSystemTypeByPath(request);
        if (SystemType.ADMIN == systemType) {
            loginRedisService.refreshToken();
        } else if (SystemType.APP == systemType) {
            loginRedisAppService.refreshToken();
        }
        return true;
    }

}
