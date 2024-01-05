package io.chou401.framework.aop;

import io.chou401.common.constant.AopConstant;
import io.chou401.common.query.DataRangeQuery;
import io.chou401.framework.utils.DataRangeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * {@code @author}  chou401
 * {@code @date} 2022/4/20
 **/
@Slf4j
@Aspect
@Component
@ConditionalOnProperty(name = "login.admin.enable", havingValue = "true", matchIfMissing = true)
public class DataRangeAop {

    @Around(AopConstant.ADMIN_POINTCUT)
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (ArrayUtils.isEmpty(args)) {
            return joinPoint.proceed();
        }
        for (Object arg : args) {
            if (arg instanceof DataRangeQuery dataRangeQuery) {
                DataRangeUtil.handleAdminLogin(dataRangeQuery);
                break;
            }
        }
        return joinPoint.proceed();
    }


}
