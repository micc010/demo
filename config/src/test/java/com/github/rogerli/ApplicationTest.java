package com.github.rogerli;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author roger.li
 */
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) // same as @Configuration @EnableAutoConfiguration @ComponentScan
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = "com.github.rogerli")
//@EnableAspectJAutoProxy(proxyTargetClass = true)
//@Order(1)
@EnableWebMvc
public class ApplicationTest {

    /**
     * jar包
     *
     * @param args
     */
    public static void main(String[] args) {
//        SpringApplication.run(ApplicationTest.class, args);
        new SpringApplicationBuilder(ApplicationTest.class).bannerMode(Banner.Mode.OFF).run(args);
    }

//    /**
//     * 普通war包
//     * @param builder
//     * @return
//     */
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(ApplicationTest.class);
//    }

    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        final CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

}
