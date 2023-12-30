package io.chou401.common.query.sys;

import io.chou401.common.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典类型查询参数
 * <p>
 * {@code @author}  chou401
 * {@code @date} 2023-11-25
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "字典类型查询参数")
public class SysDictTypeQuery extends BasePageQuery {
    private static final long serialVersionUID = 1L;

}

