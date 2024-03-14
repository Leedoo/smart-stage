package com.smart.core.enums;

import com.smart.core.entity.IErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Joe
 */
@Getter
@AllArgsConstructor
public enum ResultEnum implements IErrorCode {

    SUCCESS("200", "成功"),
    ERROR("-1000", "系统内部错误"),
    PARAM_ERROR("-2001", "请求参数缺失"),
    PARAM_TYPE_ERROR("-2002", "请求参数类型错误"),
    VALIDATION_ERROR("-3000", "数据校验错误");

    private String code;
    private String desc;
}