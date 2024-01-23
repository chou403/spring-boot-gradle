package io.chou401.framework.config;

import io.chou401.common.enums.ReadsAndWrite;
import io.chou401.framework.rule.ReadWriteRoutingDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * {@code @author}  chou401
 * {@code @date} 2024/1/22
 * {@code @description} 数据源 config
 */
@Slf4j
@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.write")
    public DataSource writeDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.read1")
    public DataSource read1DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.read2")
    public DataSource read2DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DataSource dynamicDatasource(@Qualifier("writeDataSource") DataSource writeDataSource,
                                        @Qualifier("read1DataSource") DataSource read1DataSource,
                                        @Qualifier("read2DataSource") DataSource read2DataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(ReadsAndWrite.WRITE, writeDataSource);
        targetDataSources.put(ReadsAndWrite.READ1, read1DataSource);
        targetDataSources.put(ReadsAndWrite.READ2, read2DataSource);
        ReadWriteRoutingDataSource readWriteRoutingDataSource = new ReadWriteRoutingDataSource();
        readWriteRoutingDataSource.setDefaultTargetDataSource(writeDataSource);
        readWriteRoutingDataSource.setTargetDataSources(targetDataSources);
        return readWriteRoutingDataSource;
    }

}
