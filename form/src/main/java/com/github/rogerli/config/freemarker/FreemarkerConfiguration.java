/**
 * @文件名称： FreemarkerConfiguration.java
 * @文件描述：
 * @版权所有：(C)2017-2028
 * @公司：
 * @完成日期: 2017/1/5
 * @作者 ： Roger
 */
package com.github.rogerli.config.freemarker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Properties;

/**
 * @author Roger
 * @create 2017/1/5 17:01
 */
@Configuration
public class FreemarkerConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(FreemarkerConfiguration.class);

    /**
     * freemarker配置
     *
     * @param templateLoaderPath
     * @return
     */
    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer(@Value("${spring.freemarker.template-loader-path}") String[] templateLoaderPath) {
        LOGGER.info("======FreeMarkerConfigurer======");
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPaths(templateLoaderPath);
        Properties properties = new Properties();
        properties.setProperty("defaultEncoding", "UTF-8");
        configurer.setFreemarkerSettings(properties);
        return configurer;
    }

    /**
     * freemarker视图
     *
     * @return
     */
    @Bean(name = "viewResolver")
    public FreeMarkerViewResolver viewResolver() {
        LOGGER.info("======ViewResolver======");
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setCache(true);
        viewResolver.setPrefix("");
        viewResolver.setSuffix(".ftl");
        viewResolver.setContentType("text/html;charset=UTF-8");
        viewResolver.setRequestContextAttribute("req");
        viewResolver.setExposeSpringMacroHelpers(true);
        viewResolver.setExposeRequestAttributes(true);
        viewResolver.setExposeSessionAttributes(true);
        return viewResolver;
    }

}
