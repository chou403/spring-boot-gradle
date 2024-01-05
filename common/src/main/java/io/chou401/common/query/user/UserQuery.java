package io.chou401.common.query.user;

import io.chou401.common.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户信息查询参数
 * {@code @author}  chou401
 * {@code @date} 2023-11-30
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "用户信息查询参数")
public class UserQuery extends BasePageQuery {
    private static final long serialVersionUID = 1L;

}

