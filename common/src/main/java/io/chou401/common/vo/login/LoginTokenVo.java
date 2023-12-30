package io.chou401.common.vo.login;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * {@code @author}  chou401
 * {@code @date} 2022/6/26
 **/
@Data
@Schema(description = "LoginVo")
public class LoginTokenVo {

    @Schema(description = "登录令牌")
    private String token;

}
