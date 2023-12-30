package io.chou401.framework.config.properties;

import io.chou401.common.enums.FileServerType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 上传文件属性配置
 *
 * {@code @author}  chou401
 * {@code @date} 2023/06/18
 **/
@Data
@ConfigurationProperties(prefix = "file")
public class FileProperties {

    /**
     * 文件服务类型
     */
    private FileServerType fileServerType;

}
