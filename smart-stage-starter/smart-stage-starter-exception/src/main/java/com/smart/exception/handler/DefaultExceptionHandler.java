package com.smart.exception.handler;

import com.smart.core.entity.Result;
import com.smart.core.enums.ResultEnum;
import com.smart.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.core.annotation.Order;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 公共异常拦截处理
 */
@Slf4j
@Order(100)
@ConditionalOnMissingBean(name = {"globalExceptionHandler"})
@RestControllerAdvice
public class DefaultExceptionHandler {

    /**
     * 自定义异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(CommonException.class)
    @ResponseBody
    public Object handleException(CommonException e) {
        return Result.create(e.getCode(), e.getMessage());
    }

    /**
     * 请求参数缺失异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public Object handleException(MissingServletRequestParameterException e) {
        log.error("parameter exception.", e);
        return Result.create(ResultEnum.PARAM_ERROR.getCode(), "缺少参数" + e.getParameterName());
    }

    /**
     * 请求参数类型异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public Object handleException(MethodArgumentTypeMismatchException e) {
        log.error("parameter exception.", e);
        return Result.create(ResultEnum.PARAM_TYPE_ERROR.getCode(), e.getPropertyName() + "参数类型有误");
    }

    /**
     * 未知异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(Exception e) {
        log.error("global exception.", e);
        return Result.create(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMessage());
    }
}