package com.manneia.template.utils;

import com.manneia.template.common.BaseResponse;
import com.manneia.template.enums.ErrorCode;

import java.io.Serializable;

/**
 * 返回工具类
 *
 * @author lkx
 */
@SuppressWarnings("unused")
public class ResultUtils {

    private ResultUtils() {
        throw new AssertionError("不应该实例化此工具类");
    }

    /**
     * 成功
     *
     * @param data 数据
     * @param <T>  类型
     * @return 返回响应
     */
    public static <T extends Serializable> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "ok");
    }

    /**
     * 失败
     *
     * @param errorCode 错误码
     * @return 返回响应
     */
    public static <T extends Serializable> BaseResponse<T> error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    /**
     * 失败
     *
     * @param code    响应码
     * @param message 错误信息
     * @return 返回基础响应
     */
    public static <T extends Serializable> BaseResponse<T> error(int code, T data, String message) {
        return new BaseResponse<>(code, data, message);
    }

    /**
     * 失败
     *
     * @param errorCode 错误码
     * @return 返回错误信息
     */
    public static <T extends Serializable> BaseResponse<T> error(ErrorCode errorCode, T data, String message) {
        return new BaseResponse<>(errorCode.getCode(), data, message);
    }
}
