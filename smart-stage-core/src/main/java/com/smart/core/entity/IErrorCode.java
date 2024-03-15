package com.smart.core.entity;

/**
 * 错误码基础接口
 *
 * @author Joe
 */
public interface IErrorCode extends IMessage {

    /**
     * 错误码
     */
    Integer getCode();

    /**
     * 错误码描述
     */
    default String getDesc(){
        return getKey();
    }

    /**
     * 错误码code用做I18n的key
     */
    default String getKey() {
        return getCode().toString();
    }

    @Override
    default String getMessage(Object... args) {
        return Message.getOrDefault(getKey(), getDesc(), args);
    }
}