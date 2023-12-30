package io.chou401.common.query.sys;

import io.chou401.common.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统配置查询参数
 * <p>
 * {@code @author}  chou401
 * {@code @date} 2023-11-27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "系统配置查询参数")
public class SysConfigQuery extends BasePageQuery {
    private static final long serialVersionUID = 1L;

    @Schema(description = "是否是系统字典类型")
    private Boolean isSystem;

    @Schema(description = "状态 1：正常，0：禁用")
    private Boolean status;

}

