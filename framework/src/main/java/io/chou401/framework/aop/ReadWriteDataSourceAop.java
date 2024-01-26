package io.chou401.framework.aop;

import io.chou401.framework.multiDb.ReadWriteSeparationRule;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * {@code @author}  chou401
 * {@code @date} 2024/1/22
 * {@code @description} 读写分离 aop
 */
@Slf4j
@Aspect
@Component
public class ReadWriteDataSourceAop {

    @Pointcut("!@annotation(io.chou401.framework.annotation.Writer) " +
            "&& (execution(* io.chou401..*.select*(..)) " +
            "|| execution(* io.chou401..*.get*(..)) " +
            "|| execution(* io.chou401..*.find*(..)))")
    public void readPointcut() {

    }

    @Pointcut("@annotation(io.chou401.framework.annotation.Writer) " +
            "|| execution(* io.chou401..*.insert*(..)) " +
            "|| execution(* io.chou401..*.save*(..)) " +
            "|| execution(* io.chou401..*.add*(..)) " +
            "|| execution(* io.chou401..*.update*(..)) " +
            "|| execution(* io.chou401..*.edit*(..)) " +
            "|| execution(* io.chou401..*.delete*(..)) " +
            "|| execution(* io.chou401..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        ReadWriteSeparationRule.reader();
    }

    @Before("writePointcut()")
    public void write() {
        ReadWriteSeparationRule.writer();
    }
}
