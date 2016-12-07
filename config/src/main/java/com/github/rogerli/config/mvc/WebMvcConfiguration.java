/**
 * @文件名称： WebMvcRestfulConfiguration.java
 * @文件描述：
 * @版权所有：(C)2016-2028
 * @公司：
 * @完成日期: 2016/12/5
 * @作者 ： Roger
 */
package com.github.rogerli.config.mvc;

import com.github.rogerli.config.restful.RestfulRequestMappingHandlerMapping;
import org.hibernate.validator.HibernateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author Roger
 * @description
 * @create 2016/12/5 2:58
 */
@Configuration
@ComponentScan(
        basePackages = "com.github.rogerli",
        useDefaultFilters = false,
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class, ControllerAdvice.class})
        })
@EnableWebMvc
@EnableAutoConfiguration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebMvcConfiguration.class);

    @Value("${spring.messages.basename}")
    private String BASE_NAME;
    @Value("${spring.messages.cache-seconds}")
    private int CACHE_SECONDS;

    @Override
    protected Validator getValidator() {
        LOGGER.info("======Validator======");
        LocalValidatorFactoryBean localValidatorFactoryBean =
                new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setProviderClass(HibernateValidator.class);
        localValidatorFactoryBean.setValidationMessageSource(messageSource());
        return localValidatorFactoryBean;
    }

    @Override
    protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        LOGGER.info("======RequestMappingHandlerMapping======");
        return new RestfulRequestMappingHandlerMapping();
    }

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

//    @Bean(name = "viewResolver")
//    public FreeMarkerViewResolver viewResolver() {
//        LOGGER.info("======ViewResolver======");
//        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
//        viewResolver.setCache(true);
//        viewResolver.setPrefix("");
//        viewResolver.setSuffix(".ftl");
//        viewResolver.setContentType("text/html;charset=UTF-8");
//        viewResolver.setRequestContextAttribute("request");
//        viewResolver.setExposeSpringMacroHelpers(true);
//        viewResolver.setExposeRequestAttributes(true);
//        viewResolver.setExposeSessionAttributes(true);
//        return viewResolver;
//    }



    @Bean(name = "messageSource")
    public MessageSource messageSource() {
        LOGGER.info("======MessageSource======");
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(BASE_NAME);
        messageSource.setCacheSeconds(CACHE_SECONDS);
        return messageSource;
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver commonsMultipartResolver() {
        LOGGER.info("======CommonsMultipartResolver======");
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(5000000);
        resolver.setMaxInMemorySize(8192);
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        LOGGER.info("======MappingJackson2HttpMessageConverter======");
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        List<MediaType> list = new ArrayList<MediaType>();
        list.add(new MediaType("text", "html", UTF_8));
        list.add(new MediaType("text", "json"));
        list.add(MediaType.APPLICATION_JSON);
        converter.setSupportedMediaTypes(list);
        return converter;
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        LOGGER.info("======addResourceHandlers======");
        registry.addResourceHandler("/**").addResourceLocations(new String[]{"classpath:/static/"});
    }

    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        LOGGER.info("======RequestMappingHandlerAdapter======");
        return super.requestMappingHandlerAdapter();
    }

    @Bean
    public HandlerMapping resourceHandlerMapping() {
        LOGGER.info("======HandlerMapping======");
        return super.resourceHandlerMapping();
    }

}
