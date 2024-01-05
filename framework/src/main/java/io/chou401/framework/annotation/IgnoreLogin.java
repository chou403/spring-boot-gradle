package io.chou401.framework.annotation;

import java.lang.annotation.*;

/**
 * 忽略登录的注解
 * {@code @author}  chou401
 * {@code @date} 2022/6/26
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreLogin {

}
