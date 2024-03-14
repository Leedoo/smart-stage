package com.smart.swagger.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.function.Predicate;

/**
 * swagger配置
 *
 * @author Joe
 */
@EnableConfigurationProperties({SwaggerProperties.class})
@Configuration
public class SwaggerConfig {

    @Bean
    @ConditionalOnMissingBean
    public Docket api(SwaggerProperties swaggerProperties) {
        ApiSelectorBuilder asb = new Docket(DocumentationType.OAS_30)
                .enable(swaggerProperties.getEnable())
                .apiInfo(apiInfo(swaggerProperties))
                .select();
        if (swaggerProperties.getBasePackages() != null && swaggerProperties.getBasePackages().length() != 0) {
            String[] basePackages = swaggerProperties.getBasePackages().split(",");
            if (basePackages.length == 1) {
                asb.apis(RequestHandlerSelectors.basePackage(basePackages[0]));
            } else {
                asb.apis(Arrays.stream(basePackages).map(t -> RequestHandlerSelectors.basePackage(t)).reduce(Predicate::or)
                        .orElse(p -> true));
            }
        }
        return asb.build();
    }

    /**
     * 配置基本信息
     */
    private ApiInfo apiInfo(SwaggerProperties swaggerProperties) {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .license(swaggerProperties.getLicense())
                .licenseUrl(swaggerProperties.getLicenseUrl())
                .termsOfServiceUrl(swaggerProperties.getTermsOfServiceUrl())
                .contact(new Contact(swaggerProperties.getContact().getName(), swaggerProperties.getContact().getUrl(),
                        swaggerProperties.getContact().getEmail()))
                .version(swaggerProperties.getVersion())
                .build();
    }

}