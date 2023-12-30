package io.chou401.common.page;

import io.chou401.common.query.DataRangeQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 排序查询参数
 * <p>
 * {@code @author}  chou401
 * {@code @date} 2023-11-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "排序查询参数")
public abstract class BaseOrderQuery extends DataRangeQuery {
    private static final long serialVersionUID = 1569531322874458585L;

    @Schema(description = "排序列名称")
    private String orderByColumn;

    @Schema(description = "排序方式 true：升序，false：降序")
    private Boolean orderByAsc;

}
