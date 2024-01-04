package io.chou401.login.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * {@code @author}  chou401
 * {@code @date} 2022/6/26
 **/
@Data
@Schema(description = "LoginAppDto")
public class LoginAppDto {

    @Schema(description = "小程序code")
    @NotBlank(message = "小程序code不能为空")
    private String code;

}
