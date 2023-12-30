package io.chou401.framework.config.properties;

import io.chou401.framework.utils.YamlUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 多商户行级数据权限配置
 *
 * {@code @author}  chou401
 * {@code @date} 2023/12/05
 **/
@Data
@ConfigurationProperties(prefix = "merchant-line")
public class MerchantLineProperties {

    /**
     * 商户ID列名称
     */
    private String merchantIdColumn;

    /**
     * 包含的表名称
     */
    private List<String> includeTables;

    public void setIncludeTables(List<String> includeTables) {
        this.includeTables = YamlUtil.parseListArray(includeTables);
    }

}
