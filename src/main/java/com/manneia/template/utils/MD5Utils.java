package com.manneia.template.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.manneia.template.constant.UserConstant;
import com.manneia.template.enums.ErrorCode;
import com.manneia.template.exception.BusinessException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author luokaixuan
 * @description 加密解密工具类
 * @created 2024/9/7 10:37
 */
@SuppressWarnings("unused")
@Configuration
public class MD5Utils {

    @Bean
    public MD5Utils md5Utils() {
        return new MD5Utils();
    }

    /**
     * 对传递过来的信息进行加密
     *
     * @param infoStr 传递过来的等待加密的信息
     * @return 返回加密后的信息
     */
    public String encrypt(String infoStr) {
        // 1. 判断待加密的信息是否不存在或为空字符串,若不存在则抛出异常
        if (StringUtils.isBlank(infoStr) || StringUtils.isEmpty(infoStr)) {
            throw new BusinessException(ErrorCode.PARAMS_NULL_ERROR, "未传递需要加密的信息");
        }
        // 2. 对信息进行拼接
        String encryptStr = infoStr + UserConstant.USER_SALT;
        // 3. 对拼接后的信息进行加密
        return null;
    }

    /**
     * 校验密码是否正确
     *
     * @param password        用户输入的密码
     * @param encryptPassword 数据库中存储的加密后的密码
     * @return true: 密码正确 false: 密码错误
     */
    public boolean checkPassword(String password, String encryptPassword) {
        return false;
    }

    /**
     * 检查密码是否包含至少一个小写字母、一个大写字母和一个特殊字符。
     *
     * @param password 待验证的密码字符串
     * @return 如果密码符合条件返回true，否则返回false
     */
    public boolean isValidPassword(String password) {
        String regex = "^(?=(?:.*[a-z]|.*[A-Z]|.*\\W).{2,}).+$";
        return password.matches(regex);
    }
}
