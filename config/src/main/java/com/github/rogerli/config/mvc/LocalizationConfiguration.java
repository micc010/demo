package com.github.rogerli.config.mvc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class LocalizationConfiguration {

    @Value("${spring.messages.basename}")
    private String BASE_NAME;
    @Value("${spring.messages.cache-seconds}")
    private int CACHE_SECONDS;

    /**
     * WebMvcConfigurationSupport 不能加载MessageSource
     *
     * @return
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:beanValidation", "classpath:errors");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasename(BASE_NAME);
        messageSource.setCacheSeconds(CACHE_SECONDS);
        return messageSource;
    }

    //    @Bean(name = "messageSource")
//    public MessageSource messageSource() {
//        LOGGER.info("======MessageSource======");
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setBasename(BASE_NAME);
//        messageSource.setCacheSeconds(CACHE_SECONDS);
//        return messageSource;
//    }

}