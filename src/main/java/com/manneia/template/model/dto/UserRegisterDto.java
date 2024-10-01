package com.manneia.template.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * @author luokaixuan
 * @description 用户注册请求参数
 * @created 2024/9/6 19:16
 */
@Data
public class UserRegisterDto implements Serializable {

    @NotNull(message = "账号不能为空")
    private String userAccount;

    @NotNull(message = "用户名不能为空")
    public String username;

    @NotNull(message = "密码不能为空")
    public String password;

    @NotNull(message = "确认密码不能为空")
    public String checkPassword;

}
