package io.chou401.framework.config;

import io.chou401.framework.config.properties.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/7/9
 **/
@Configuration
@EnableConfigurationProperties({
        BootProperties.class,
        LoginProperties.class,
        LoginAdminProperties.class,
        LoginAppProperties.class,
        LoginCommonProperties.class,
        FileProperties.class,
        LocalFileProperties.class,
        OpenApiProperties.class,
        XssProperties.class,
        WxMpProperties.class,
        OssProperties.class,
        MerchantLineProperties.class,
        NotAuthProperties.class
})
public class PropertiesConfig {

}
