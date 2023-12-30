package io.chou401.framework.annotation;

import java.lang.annotation.*;

/**
 * 需要登录的注解
 *
 * {@code @author}  chou401
 * {@code @date} 2023/11/22
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {

}
