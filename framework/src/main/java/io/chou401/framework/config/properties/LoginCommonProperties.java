package io.chou401.framework.config.properties;

import io.chou401.framework.utils.YamlUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * {@code @author}  chou401
 * {@code @date} 2022/6/26
 **/
@Data
@ConfigurationProperties(prefix = "login.common")
public class LoginCommonProperties {

    /**
     * 是否启用拦截器
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
