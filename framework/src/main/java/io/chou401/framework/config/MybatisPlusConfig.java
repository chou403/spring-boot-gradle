package io.chou401.framework.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DataPermissionInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import io.chou401.framework.config.properties.MerchantLineProperties;
import io.chou401.framework.mybatis.plugins.handler.DataScopeHandler;
import io.chou401.framework.mybatis.plugins.handler.MerchantLineHandler;
import io.chou401.framework.utils.SystemTypeUtil;
import io.chou401.framework.utils.login.LoginUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.List;

/**
 * MybatisPlus配置
 * {@code @author}  chou401
 * {@code @date} 2023/11/25
 **/
@Slf4j
@Configuration
@EnableTransactionManagement
public class MybatisPlusConfig {

    @Resource(name = "dynamicDatasource")
    private DataSource dynamicDatasource;

    private final MerchantLineProperties merchantLineProperties;

    public MybatisPlusConfig(MerchantLineProperties merchantLineProperties) {
        this.merchantLineProperties = merchantLineProperties;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDatasource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        configuration.setLogImpl(StdOutImpl.class);
        sqlSessionFactoryBean.setConfiguration(configuration);

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(dynamicDatasource);
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 数据范围权限
        interceptor.addInnerInterceptor(new DataPermissionInterceptor(new DataScopeHandler()));
        // 多商户插件，默认关闭，如有需要，放开注释即可
        // interceptor.addInnerInterceptor(new MerchantLineInnerInterceptor(merchantLineHandler()));
        // 攻击 SQL 阻断解析器,防止全表更新与删除
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
//        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
//
//    /**
//     * 分页设置
//     * @return  分页拦截器
//     */
//    @Bean
//    public PaginationInnerInterceptor paginationInnerInterceptor() {
//        PaginationInnerInterceptor innerInterceptor = new PaginationInnerInterceptor();
//        // 单页分页条数限制
//        innerInterceptor.setMaxLimit(100L);
//        // 数据库类型
//        innerInterceptor.setDbType(DbType.MYSQL);
//        // 当超过最大页数时不会报错
//        innerInterceptor.setOverflow(true);
//        return innerInterceptor;
//    }

    /**
     * 配置多商户插件
     * 如果不需要删除即可
     *
     * @return
     */
    @Bean
    public MerchantLineHandler merchantLineHandler() {
        log.info("merchantLineProperties:" + merchantLineProperties);
        return new MerchantLineHandler() {
            @Override
            public Expression getMerchantId() {
                // TODO 可以在LoginUtil中添加获取商户ID的方法
                // LoginUtil.getMerchantId();
                return new LongValue(1);
            }

            @Override
            public String getMerchantIdColumn() {
                return merchantLineProperties.getMerchantIdColumn();
            }

            @Override
            public boolean ignoreTable(String tableName) {
                List<String> includeTables = merchantLineProperties.getIncludeTables();
                // 判断如果包含指定的表，则不忽略，否则忽略
                if (CollectionUtils.isNotEmpty(includeTables)) {
                    if (includeTables.contains(tableName)) {
                        return false;
                    }
                }
                return true;
            }

            /**
             * 判断系统类型和系统角色，是否忽略添加商户ID
             * 添加商户ID的情况
             * 1：是管理后台的请求
             * 2：非管理员
             * 除此之外，都忽略
             *
             * @return
             */
            @Override
            public boolean ignoreMerchantId() {
                try {
                    if (SystemTypeUtil.isAdminSystem() && LoginUtil.isNotAdmin()) {
                        return false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        };
    }

}
