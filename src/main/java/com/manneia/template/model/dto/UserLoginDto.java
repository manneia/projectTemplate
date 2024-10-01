package com.manneia.template.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author luokaixuan
 * @description 用户登录请求参数
 * @created 2024/9/7 17:02
 */
@Data
public class UserLoginDto {

    @NotNull(message = "账号不能为空")
    private String userAccount;

    @NotNull(message = "密码不能为空")
    private String userPassword;
}
