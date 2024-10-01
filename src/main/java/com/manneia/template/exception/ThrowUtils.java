package com.manneia.template.exception;

import com.manneia.template.enums.ErrorCode;

/**
 * 抛异常工具类
 *
 * @author lkx
 */
@SuppressWarnings("unused")
public class ThrowUtils {

    private ThrowUtils() {
        throw new AssertionError("不允许实例化");
    }

    /**
     * 条件成立则抛异常
     *
     * @param condition 判定异常条件
     * @param runtimeException 异常类型
     */
    public static void throwIf(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            throw runtimeException;
        }
    }

    /**
     * 条件成立则抛异常
     *
     * @param condition 判定异常条件
     * @param errorCode 异常类型
     */
    public static void throwIf(boolean condition, ErrorCode errorCode) {
        throwIf(condition, new BusinessException(errorCode));
    }

    /**
     * 条件成立则抛异常
     *
     * @param condition 判定异常条件
     * @param errorCode 异常类型
     * @param message 异常信息
     */
    public static void throwIf(boolean condition, ErrorCode errorCode, String message) {
        throwIf(condition, new BusinessException(errorCode, message));
    }
}
