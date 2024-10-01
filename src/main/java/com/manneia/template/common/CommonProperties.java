package com.manneia.template.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author luokaixuan
 * @description com.manneia.template.common
 * @created 2024/9/21 21:06
 */
@Data
@Component
public class CommonProperties {

    // 接口用户名
    @Value("${template.username}")
    private String username;

    // 接口密码
    @Value("${template.password}")
    private String password;
}
