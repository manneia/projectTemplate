package com.manneia.template.constant;

import lombok.Data;

/**
 * @author luokaixuan
 * @description 常用字符串常量
 * @created 2024/9/7 10:35
 */
@Data
public class UserConstant {
    private UserConstant() {
        throw new AssertionError("Cannot instantiate a utility class.");
    }

    /**
     * 用户盐值.用户对信息进行加密
     */
    public static final String USER_SALT = "manneia_salt";

    /**
     * 用户状态, 0 未登录, 1 已登录
     */
    public static final String USER_STATUS_LOGIN = "user_login";
}
