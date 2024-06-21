package com.smart.stage.i18n.config;

import com.smart.stage.core.entity.Message;
import com.smart.stage.i18n.entity.I18nMessage;
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
        return new I18nMessage(messageSource);
    }
}