package com.github.rogerli.config.mvc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class LocalizationConfiguration {

    @Value("${spring.messages.basename}")
    private String baseName;
    @Value("${spring.messages.cache-seconds}")
    private int cacheSeconds;
    @Value("${spring.messages.encoding}")
    private String encoding;

    @Bean(name = "messageSource")
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding(encoding);
        messageSource.setBasename(baseName);
        messageSource.setCacheSeconds(cacheSeconds);
        return messageSource;
    }

}