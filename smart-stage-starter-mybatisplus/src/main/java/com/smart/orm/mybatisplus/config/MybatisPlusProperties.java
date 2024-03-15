package com.smart.orm.mybatisplus.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("stage.mybatis-plus")
public class MybatisPlusProperties {

    private String pageDbType;

    public String getPageDbType() {
        return pageDbType;
    }

    public void setPageDbType(String pageDbType) {
        this.pageDbType = pageDbType;
    }
}