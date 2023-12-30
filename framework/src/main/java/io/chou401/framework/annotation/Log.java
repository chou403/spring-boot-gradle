package io.chou401.framework.annotation;


import io.chou401.common.enums.SysLogType;

/**
 * {@code @author}  chou401
 * {@code @date} 2022/8/3
 **/
public @interface Log {

    /**
     * 描述
     *
     * @return
     */
    String value() default "";


    SysLogType type() default SysLogType.OTHER;

}
