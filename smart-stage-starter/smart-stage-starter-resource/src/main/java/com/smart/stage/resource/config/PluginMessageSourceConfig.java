package com.smart.stage.resource.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 扩展加载插件国际化资源文件
 *
 * @see org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration
 */
@ConditionalOnMissingBean(name = {"pluginMessageSource"})
@Configuration
public class PluginMessageSourceConfig {
    private static final Logger log = LoggerFactory.getLogger(PluginMessageSourceConfig.class);

    private static final String[] PLUGIN_DIRS = {"plugin"};
    private static final String SUFFIX = ".properties";

    @Value("${spring.messages.basename:messages}")
    private String baseName;

    @Primary
    @Bean
    public MessageSource pluginMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        List<String> baseNameList = new ArrayList<>();

        // 获得用户配置
        String[] baseNames = baseName.split(",");
        baseNameList.addAll(Arrays.asList(baseNames));

        // 获取所有插件的资源文件目录
        for (String dir : PLUGIN_DIRS) {
            parseResources(dir + "/*/" + baseNames[0] + SUFFIX, dir + "/(.*?)/" + baseNames[0] + "\\" + SUFFIX, baseNameList);
        }

        baseNames = baseNameList.toArray(new String[0]);
        log.debug("plugin message paths:{}", Arrays.toString(baseNames));
        messageSource.setBasenames(baseNames);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    private void parseResources(String location, String regex, List<String> baseNameList) {
        Resource[] resources;
        try {
            resources = new PathMatchingResourcePatternResolver().getResources(ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + location);
        } catch (IOException e) {
            log.error("", e);
            return;
        }

        Pattern pattern = Pattern.compile(regex);
        for (Resource resource : resources) {
            String path;
            try {
                path = resource.getURL().getPath();
            } catch (IOException e) {
                log.error("", e);
                continue;
            }
            Matcher matcher = pattern.matcher(path);
            if (matcher.find()) {
                baseNameList.add(location.replace("*", matcher.group(1)).replace(SUFFIX, ""));
            }
        }
    }
}
