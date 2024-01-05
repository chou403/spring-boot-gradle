package io.chou401.common.query.sys;

import io.chou401.common.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统角色查询参数
 * {@code @author}  chou401
 * {@code @date} 2022-12-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "系统角色查询参数")
public class SysRoleQuery extends BasePageQuery {
    private static final long serialVersionUID = 1L;

    @Schema(description = "角色名称")
    private String name;

    @Schema(description = "角色编码")
    private String code;

}

