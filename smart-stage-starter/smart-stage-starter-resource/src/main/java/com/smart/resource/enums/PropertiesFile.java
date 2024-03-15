package com.smart.resource.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.function.Function;

public enum PropertiesFile {

    YML(".yml", PropertiesFile::loadYamlFile),
    YAML(".yaml", PropertiesFile::loadYamlFile),
    PROPERTIES(".properties", PropertiesFile::loadPropertiesFile);

    private String type;
    private final Function<Resource, Properties> parser;

    PropertiesFile(String type, Function<Resource, Properties> parser) {
        this.type = type;
        this.parser = parser;
    }

    public String getType() {
        return type;
    }

    public Function<Resource, Properties> getParser() {
        return parser;
    }

    private static final Logger log = LoggerFactory.getLogger(PropertiesFile.class);

    private static Properties loadPropertiesFile(Resource resource) {
        Properties properties = new Properties();
        try (InputStream inputStream = resource.getInputStream()) {
            properties.load(inputStream);
        } catch (IOException e) {
            log.error("Error loading properties file: {}", e.getMessage(), e);
        }
        return properties;
    }

    public static Properties loadYamlFile(Resource resource) {
        Properties properties = new Properties();
        YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
        EncodedResource encodedResource = new EncodedResource(resource, StandardCharsets.UTF_8);
        factoryBean.setResources(encodedResource.getResource());
        properties.putAll(factoryBean.getObject());
        return properties;
    }
}
