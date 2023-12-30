package io.chou401.framework.utils;

import io.chou401.common.constant.CommonConstant;
import org.slf4j.MDC;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/11/26
 **/
public class TraceIdUtil {

    /**
     * 获取当前请求日志链路ID
     *
     * @return
     */
    public static String getTraceId() {
        return MDC.get(CommonConstant.TRACE_ID);
    }

}
