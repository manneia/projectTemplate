package com.manneia.template.exception;

import com.manneia.template.common.BaseResponse;
import com.manneia.template.enums.ErrorCode;
import com.manneia.template.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.Serializable;

/**
 * @author luokaixuan
 * @description 全局异常处理器
 * @created 2024/9/6 18:53
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public <T extends Serializable> BaseResponse<T> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException", e);
        return ResultUtils.error(e.getCode(), null, e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public <T extends Serializable> BaseResponse<T> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR.getCode(), null, ErrorCode.SYSTEM_ERROR.getMessage());
    }

}
