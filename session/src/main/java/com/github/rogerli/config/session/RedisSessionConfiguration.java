package com.github.rogerli.config.session;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
public class RedisSessionConfiguration {

//    @Bean
//    public HttpSessionStrategy httpSessionStrategy() {
//        return new CustomHeaderHttpSessionStrategy();
//    }

}
