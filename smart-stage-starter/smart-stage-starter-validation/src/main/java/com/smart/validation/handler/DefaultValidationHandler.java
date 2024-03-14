package com.smart.validation.handler;

import com.smart.core.entity.Result;
import com.smart.core.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.core.annotation.Order;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 验证异常拦截
 */
@Slf4j
@Order(50)
@ConditionalOnMissingBean(name = {"globalExceptionHandler"})
@RestControllerAdvice
public class DefaultValidationHandler {

    /**
     * 验证异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Object handleException(MethodArgumentNotValidException e) {
        List<FieldError> errorList = e.getBindingResult().getFieldErrors();
        String message = errorList.stream().map(t -> t.getField() + ":" + t.getDefaultMessage()).collect(Collectors.joining(";"));
        return Result.create(ResultEnum.VALIDATION_ERROR.getCode(), message);
    }
}