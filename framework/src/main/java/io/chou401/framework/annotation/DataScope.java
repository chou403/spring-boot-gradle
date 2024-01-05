package io.chou401.framework.annotation;

import java.lang.annotation.*;

/**
 * 数据权限范围注解
 * {@code @author}  chou401
 * {@code @date} 2023/12/02
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {

    // TODO 如果是商城，则可新增商家表别名，和对应的筛选列，如果有部门数据权限，则新增部门表和列别名

    /**
     * 用户表别名
     */
    String userAlias() default "";

    /**
     * 筛选的userId列
     */
    String userIdColumn() default "id";

}
