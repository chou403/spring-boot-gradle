package io.chou401.common.query.sys;

import io.chou401.common.query.DataRangeQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统菜单查询参数
 * <p>
 * {@code @author}  chou401
 * {@code @date} 2022-12-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "系统菜单查询参数")
public class SysMenuQuery extends DataRangeQuery {
    private static final long serialVersionUID = 1L;

    @Schema(description = "菜单名称")
    private String name;

    @Schema(description = "权限编码")
    private String code;

    @Schema(description = "菜单类型，1：菜单，2：外链，3：权限")
    private Integer type;

    @Schema(description = "前端路由地址")
    private String routeUrl;

    @Schema(description = "状态，0：禁用，1：启用")
    private Boolean status;

}

