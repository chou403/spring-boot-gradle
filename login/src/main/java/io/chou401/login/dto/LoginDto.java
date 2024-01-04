package io.chou401.login.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;


/**
 * {@code @author}  chou401
 * {@code @date} 2022/6/26
 **/
@Data
@Schema(description = "AdminLoginDto")
public class LoginDto {

    @Schema(description = "用户名", example = "admin")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @Schema(description = "密码", example = "e10adc3949ba59abbe56e057f20f883e")
    @NotBlank(message = "密码不能为空")
    private String password;

}
