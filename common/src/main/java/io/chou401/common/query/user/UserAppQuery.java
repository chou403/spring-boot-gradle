package io.chou401.common.query.user;

import io.chou401.common.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * App用户信息查询参数
 * {@code @author}  chou401
 * {@code @date} 2023-11-25
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "App用户信息查询参数")
public class UserAppQuery extends BasePageQuery {
    private static final long serialVersionUID = 1L;

}

