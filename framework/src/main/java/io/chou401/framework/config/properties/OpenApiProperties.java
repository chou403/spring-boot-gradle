package io.chou401.framework.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Swagger属性配置
 * {@code @author}  chou401
 * {@code @date} 2022/03/15
 **/
@Data
@ConfigurationProperties(prefix = "openapi")
public class OpenApiProperties {

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 团队地址
     */
    private String termsOfService;

    /**
     * 联系人名称
     */
    private String contactName;

    /**
     * 联系人URL
     */
    private String contactUrl;

    /**
     * 联系人Email
     */
    private String contactEmail;

    /**
     * 版本
     */
    private String version;

    /**
     * 扩展描述
     */
    private String externalDescription;

    /**
     * 扩展Url
     */
    private String externalUrl;

}
