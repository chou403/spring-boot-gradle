package io.chou401.framework.multiDb;

import io.chou401.common.enums.ReadsAndWrite;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@code @author}  chou401
 * {@code @date} 2024/1/22
 * {@code @description} 读写分离 规则
 */
public class ReadWriteSeparationRule {
    private static final ThreadLocal<ReadsAndWrite> contextHolder = new ThreadLocal<>();

    private static final AtomicInteger counter = new AtomicInteger(-1);

    public static void set(ReadsAndWrite nodeType) {
        contextHolder.set(nodeType);
    }

    public static ReadsAndWrite get() {
        return contextHolder.get();
    }

    /**
     * 多个写节点也可以做简单的负载均衡
     */
    public static void writer() {
        set(ReadsAndWrite.WRITE);
    }

    /**
     * 读简单的1:2权重负载均衡
     */
    public static void reader() {
        int index = counter.incrementAndGet() % 3;
        if (counter.get() > 1000) {
            counter.set(-1);
        }
        if (index == 0) {
            set(ReadsAndWrite.READ1);
        } else {
            set(ReadsAndWrite.READ2);
        }
    }
}
