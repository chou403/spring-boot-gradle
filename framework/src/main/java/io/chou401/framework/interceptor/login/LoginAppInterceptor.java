package io.chou401.framework.interceptor.login;

import io.chou401.common.cache.LoginAppCache;
import io.chou401.common.vo.login.LoginAppVo;
import io.chou401.framework.annotation.Vip;
import io.chou401.framework.exception.AuthException;
import io.chou401.framework.exception.LoginTokenException;
import io.chou401.framework.interceptor.BaseExcludeMethodInterceptor;
import io.chou401.framework.utils.TokenUtil;
import io.chou401.framework.utils.login.LoginAppUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {@code @author}  chou401
 * {@code @date} 2022/6/26
 **/
@Slf4j
public class LoginAppInterceptor extends BaseExcludeMethodInterceptor {

    @Override
    protected boolean preHandleMethod(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
        // 获取token
        String token = TokenUtil.getToken();
        LoginAppVo loginAppVo = null;
        if (StringUtils.isNotBlank(token)) {
            // 获取登录用户信息
            loginAppVo = LoginAppUtil.getLoginVo(token);
            if (loginAppVo != null) {
                // 将APP移动端的登录信息保存到当前线程中
                LoginAppCache.set(loginAppVo);
            }
        }
        // 如果不存在@Login注解，则跳过
        boolean existLoginAnnotation = isExistLoginAnnotation(handlerMethod);
        if (!existLoginAnnotation) {
            return true;
        }
        // 登录校验
        if (StringUtils.isBlank(token)) {
            throw new LoginTokenException("请登录后再操作");
        }
        // 校验登录用户信息
        if (loginAppVo == null) {
            throw new LoginTokenException("登录已过期或登录信息不存在，请重新登录");
        }
        // 校验VIP等级
        Vip vip = handlerMethod.getMethodAnnotation(Vip.class);
        if (vip != null) {
            boolean isVip = loginAppVo.isVip();
            if (!isVip) {
                throw new AuthException("只有VIP才能操作");
            }
            // 判断VIP是否有对应的权限
            Integer vipLevel = loginAppVo.getVipLevel();
            boolean isOk = hasVipPermission(vip, vipLevel);
            if (!isOk) {
                throw new AuthException("当前VIP权限不足");
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoginAppCache.remove();
    }

    /**
     * 是否有VIP权限
     *
     * @param vip
     * @param vipLevel
     * @return
     */
    private boolean hasVipPermission(Vip vip, Integer vipLevel) {
        if (vipLevel == null || vipLevel <= 0) {
            throw new AuthException("VIP等级错误");
        }
        int eq = vip.eq();
        int gt = vip.gt();
        int lt = vip.lt();
        int ge = vip.ge();
        int le = vip.le();
        int[] levels = vip.levels();
        if (eq != 0 && vipLevel.equals(eq)) {
            return true;
        }
        if (gt != 0 && vipLevel > gt) {
            return true;
        }
        if (lt != 0 && vipLevel < lt) {
            return true;
        }
        if (ge != 0 && vipLevel >= ge) {
            return true;
        }
        if (le != 0 && vipLevel <= le) {
            return true;
        }
        if (ArrayUtils.isNotEmpty(levels) && ArrayUtils.contains(levels, vipLevel)) {
            return true;
        }
        return false;
    }

}
