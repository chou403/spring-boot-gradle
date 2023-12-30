package io.chou401.framework.config.properties;

import io.chou401.framework.utils.YamlUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 框架公共配置
 *
 * {@code @author}  chou401
 * {@code @date} 2023/12/03
 **/
@Data
@ConfigurationProperties(prefix = "boot")
public class BootProperties {

    /**
     * 排除的路径
     */
    private List<String> excludePaths;

    public void setExcludePaths(List<String> excludePaths) {
        this.excludePaths = YamlUtil.parseListArray(excludePaths);
    }

}
