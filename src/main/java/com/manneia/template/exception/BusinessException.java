package com.manneia.template.exception;

import com.manneia.template.enums.ErrorCode;
import lombok.Getter;

/**
 * @author luokaixuan
 * @description 自定义异常处理类
 * @created 2024/9/6 18:50
 */
@Getter
@SuppressWarnings("unused")
public class BusinessException extends RuntimeException {
    /**
     * 错误码
     */
    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

}
