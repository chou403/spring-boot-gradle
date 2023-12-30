package io.chou401.framework.config.properties;

import io.chou401.framework.utils.YamlUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * {@code @author}  chou401
 * {@code @date} 2022/6/18
 **/
@Data
@ConfigurationProperties(prefix = "log-aop")
public class LogAopProperties {

    /**
     * 是否启用
     */
    private boolean enable = true;

    /**
     * 是否打印日志
     */
    private boolean printLog = true;

    /**
     * 是否打印请求头日志
     */
    private boolean printHeadLog = false;

    /**
     * 排除的路径
     */
    private List<String> excludePaths;


    public void setExcludePaths(List<String> excludePaths) {
        this.excludePaths = YamlUtil.parseListArray(excludePaths);
    }

}
