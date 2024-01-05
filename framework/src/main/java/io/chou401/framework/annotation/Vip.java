package io.chou401.framework.annotation;

import java.lang.annotation.*;

/**
 * VIP级别注解
 *
 * {@code @author}  chou401
 * {@code @date} 2023/11/22
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Login
public @interface Vip {

    /**
     * VIP级别描述
     */
    String description() default "";

    /**
     * 等于某个VIP级别
     */
    int eq() default 0;

    /**
     * 大于某个VIP级别
     */
    int gt() default 0;

    /**
     * 小于某个VIP级别
     */
    int lt() default 0;

    /**
     * 大于等于某个VIP级别
     */
    int ge() default 0;

    /**
     * 小于等于某个VIP级别
     */
    int le() default 0;

    /**
     * 包含那些VIP级别
     */
    int[] levels() default {};

}
