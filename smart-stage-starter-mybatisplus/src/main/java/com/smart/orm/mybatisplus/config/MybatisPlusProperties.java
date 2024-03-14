package com.smart.orm.mybatisplus.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("smart.mybatis-plus")
public class MybatisPlusProperties {

    private String pageDbType;
}