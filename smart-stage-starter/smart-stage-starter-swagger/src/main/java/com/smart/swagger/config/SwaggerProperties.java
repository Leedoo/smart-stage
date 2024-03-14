package com.smart.swagger.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * SwaggerProperties
 *
 * @author Joe
 */
@Data
@ConfigurationProperties("smart.swagger")
public class SwaggerProperties {

    private Boolean enable = Boolean.TRUE;
    private String basePackages;

    private String version;
    private String title;
    private String description;
    private String termsOfServiceUrl;
    private String license;
    private String licenseUrl;
    private Contact contact = new Contact();

    @Data
    @NoArgsConstructor
    public class Contact {
        private String name;
        private String url;
        private String email;
    }
}