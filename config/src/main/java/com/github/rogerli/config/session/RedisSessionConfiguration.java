package com.github.rogerli.config.session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

@Configuration
@EnableRedisHttpSession
public class RedisSessionConfiguration {

    @Bean
    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setTimeout(60*60);// 有效时间1个小时
        return jedisConnectionFactory;
    }


//    @Bean
//    public HttpSessionStrategy httpSessionStrategy() {
//        return new CustomHeaderHttpSessionStrategy();
//    }

}
