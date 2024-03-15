package com.smart.core.enums;

import com.smart.core.entity.IErrorCode;

/**
 * @author Joe
 */
public enum ResultEnum implements IErrorCode {

    SUCCESS(200, "成功"),
    ERROR(-1000, "系统内部错误"),
    VALIDATION_ERROR(-2000, "参数校验错误");

    private Integer code;
    private String desc;

    ResultEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}