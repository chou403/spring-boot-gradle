package io.chou401.framework.annotation;


import io.chou401.common.enums.SysLogType;

import java.lang.annotation.*;

/**
 * {@code @author}  chou401
 * {@code @date} 2022/8/3
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 描述
     */
    String value() default "";


    SysLogType type() default SysLogType.OTHER;

}
