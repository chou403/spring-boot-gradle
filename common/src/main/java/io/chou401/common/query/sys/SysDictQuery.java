package io.chou401.common.query.sys;

import io.chou401.common.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * 字典数据查询参数
 *
 * {@code @author}  chou401
 * {@code @date} 2023-11-25
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "字典数据查询参数")
public class SysDictQuery extends BasePageQuery {
    private static final long serialVersionUID = 1L;

    @Schema(description = "字典类型code")
    @NotBlank(message = "字典类型不能为空")
    private String dictCode;

    @Schema(description = "状态 1：启用，0：禁用")
    private Boolean status;

}

