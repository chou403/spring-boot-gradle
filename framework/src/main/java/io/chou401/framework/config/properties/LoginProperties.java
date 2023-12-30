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
@ConfigurationProperties(prefix = "login")
public class LoginProperties {

    /**
     * 排除的路径
     */
    private List<String> excludePaths;

    public void setExcludePaths(List<String> excludePaths) {
        this.excludePaths = YamlUtil.parseListArray(excludePaths);
    }


}
