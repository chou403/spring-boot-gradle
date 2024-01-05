package io.chou401.framework.config.properties;

import io.chou401.framework.utils.YamlUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 上传文件属性配置
 * {@code @author}  chou401
 * {@code @date} 2023/06/18
 **/
@Data
@ConfigurationProperties(prefix = "not-auth")
public class NotAuthProperties {

    /**
     * 是否启用登录校验
     */
    private boolean enable;

    /**
     * 包含的路径
     */
    private List<String> includePaths;

    public void setExcludePaths(List<String> includePaths) {
        this.includePaths = YamlUtil.parseListArray(includePaths);
    }

}
