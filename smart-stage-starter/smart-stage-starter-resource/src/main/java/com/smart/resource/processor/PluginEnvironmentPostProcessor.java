package com.smart.resource.processor;

import com.smart.resource.enums.PropertiesFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.Properties;
import java.util.function.Function;

/**
 * 扩展加载插件配置文件
 */
public class PluginEnvironmentPostProcessor implements EnvironmentPostProcessor {
    private static final Logger log = LoggerFactory.getLogger(PluginEnvironmentPostProcessor.class);
    private static final String[] PLUGIN_DIRS = {"plugin"};

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

        // 解析默认配置文件，如：application.yml
        parseResources(environment, resourcePatternResolver, "");

        // 解析activeProfiles配置文件，如：application-dev.yml
        for (String profile : environment.getActiveProfiles()) {
            parseResources(environment, resourcePatternResolver, "-" + profile);
        }
    }

    private void parseResources(ConfigurableEnvironment environment, ResourcePatternResolver resourcePatternResolver, String profile) {
        for (String dir : PLUGIN_DIRS) {
            for (PropertiesFile file : PropertiesFile.values()) {
                String location = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + dir + "/*/application" + profile + file.getType();
                parseResources(environment, resourcePatternResolver, location, file.getParser());
            }
        }
    }

    private void parseResources(ConfigurableEnvironment environment, ResourcePatternResolver resourcePatternResolver, String location,
                                Function<Resource, Properties> parser) {
        try {
            Resource[] resources = resourcePatternResolver.getResources(location);
            for (Resource resource : resources) {
                if (resource.exists() && resource.isReadable()) {
                    Properties properties = parser.apply(resource);
                    if (!properties.isEmpty()) {
                        MutablePropertySources propertySources = environment.getPropertySources();
                        String pathName = resource.getURL().getPath();
                        // 优先级低于系统环境
                        propertySources.addAfter(StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME, new PropertiesPropertySource(pathName
                                , properties));
                    }
                }
            }
        } catch (IOException e) {
            log.error("", e);
        }
    }
}


