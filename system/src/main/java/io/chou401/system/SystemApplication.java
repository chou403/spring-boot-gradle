package io.chou401.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * {@code @author}  chou401
 * {@code {@code @date}} 2023/12/26
 * {@code @description} ${DESCRIPTION}
 */
@SpringBootApplication
@MapperScan("io.chou401.common.mapper")
@ComponentScan(basePackages = "io.chou401")
public class SystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }
}