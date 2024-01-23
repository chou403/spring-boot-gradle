package io.chou401.framework.rule;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

/**
 * {@code @author}  chou401
 * {@code @date} 2024/1/22
 * {@code @description} 读写分离 路由
 */
public class ReadWriteRoutingDataSource extends AbstractRoutingDataSource {

    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        return ReadWriteSeparationRule.get();
    }
}
