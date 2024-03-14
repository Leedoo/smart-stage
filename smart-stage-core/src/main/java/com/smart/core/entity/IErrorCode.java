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
    String getCode();

    /**
     * 错误码code用做I18n的key
     */
    @Override
    default String getKey() {
        return getCode();
    }
}