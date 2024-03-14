package com.smart.core.entity;

import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;

/**
 * 消息处理抽象类，支持动态传参，支持I18n
 * 注：国际化能力通过继承它实现，具体查看I18nMessage
 *
 * @author Joe
 */
@Slf4j
public abstract class Message {

    /**
     * 定义默认实现
     */
    protected static final Message DEFAULT = new Message() {

        @Override
        public String getMessageOrDefault(String key, String defaultValue, Object... args) {
            if (args == null || args.length == 0) {
                return defaultValue;
            }
            try {
                return MessageFormat.format(defaultValue, args);
            } catch (Exception e) {
                log.error(e.getMessage());
                return defaultValue;
            }
        }
    };

    /**
     * 当前实例，可自定义通过setLocal()覆盖
     */
    private static Message local = DEFAULT;

    public void setLocal(Message message) {
        local = message;
    }

    public static String get(String key, Object... args) {
        return getOrDefault(key, key, args);
    }

    public static String getOrDefault(String key, String defaultValue, Object... args) {
        return local.getMessageOrDefault(key, defaultValue, args);
    }

    public abstract String getMessageOrDefault(String key, String defaultValue, Object... args);
}