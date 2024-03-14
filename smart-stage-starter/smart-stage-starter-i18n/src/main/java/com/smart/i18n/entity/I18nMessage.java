package com.smart.i18n.entity;

import com.smart.core.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;
import java.util.Objects;

/**
 * 国际化支持
 */
@Slf4j
public class I18nMessage extends Message {

    private static MessageSource messageSource;

    public I18nMessage(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public static String get(Locale locale, String key, Object... args) {
        return getOrDefault(locale, key, key, args);
    }

    public static String getOrDefault(Locale locale, String key, String defaultValue, Object... args) {
        return getLocaleMessageOrDefault(locale, key, defaultValue, args);
    }

    private static String getLocaleMessageOrDefault(Locale locale, String key, String defaultValue, Object... args) {
        String message = getLocaleMessage(locale, key, args);
        if (Objects.isNull(message)) {
            return DEFAULT.getMessageOrDefault(key, defaultValue, args);
        } else {
            return message;
        }
    }

    private static String getLocaleMessage(Locale locale, String key, Object... args) {
        try {
            if (log.isDebugEnabled()) {
                log.debug("use country[{}]-language[{}]", locale.getCountry(), locale.getLanguage());
            }
            return messageSource.getMessage(key, args, locale);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return null;
        }
    }

    @Override
    public String getMessageOrDefault(String key, String defaultValue, Object... args) {
        return getLocaleMessageOrDefault(LocaleContextHolder.getLocale(), key, defaultValue, args);
    }
}