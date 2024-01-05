package io.chou401.framework.annotation;

import java.lang.annotation.*;

/**
 * 权限注解
 * {@code @author}  chou401
 * {@code @date} 2022/6/26
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {

    /**
     * 权限编码
     */
    String value();

    /**
     * 角色编码
     */
    String role() default "";

    /**
     * 角色编码数组
     */
    String[] roles() default {};

}
