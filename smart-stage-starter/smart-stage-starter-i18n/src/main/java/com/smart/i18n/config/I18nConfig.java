package com.smart.i18n.config;

import com.smart.core.entity.Message;
import com.smart.i18n.entity.I18nMessage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnBean({MessageSource.class})
public class I18nConfig {

    @Bean
    @ConditionalOnMissingBean
    public Message i18nMessage(MessageSource messageSource) {
        Message message = new I18nMessage(messageSource);
        message.setLocal(message);
        return message;
    }
}