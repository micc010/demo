package com.github.rogerli.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;

/**
 * redis cache配置
 */
@Configuration
@EnableCaching
public class RedisCacheConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisCacheConfiguration.class);


    /**
     * spring redis 缓存管理器.
     *
     * @param redisTemplate
     * @return
     */
    @Bean(name = "cacheManager")
    public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
        LOGGER.debug("======Creating spring redis cache manager======");
        CacheManager cacheManager = new RedisCacheManager(redisTemplate);
        return cacheManager;
    }

    @Bean(name = "JedisConnectionFactory")
    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        return jedisConnectionFactory;
    }

    /**
     * RedisTemplate缓存操作类,类似于jdbcTemplate的一个类;
     * 虽然CacheManager也能获取到Cache对象，但是操作起来没有那么灵活；
     *
     * @param factory : 通过Spring进行注入，参数在application.properties进行配置；
     * @return
     */
    @Bean
    @ConditionalOnBean(JedisConnectionFactory.class)
    public RedisTemplate<String, String> redisTemplate(@Qualifier("JedisConnectionFactory") JedisConnectionFactory factory) {
        LOGGER.debug("======Creating redis template======");
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
        redisTemplate.setConnectionFactory(factory);

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

        RedisSerializer<String> redisSerializer = new StringRedisSerializer();//Long类型不可以，会出现异常信息;
        redisTemplate.setKeySerializer(redisSerializer);
//        redisTemplate.setHashKeySerializer(redisSerializer);

        return redisTemplate;
    }

    /**
     * 自定义key.
     * 此方法将会根据类名+方法名+所有参数的值生成唯一的一个key,即使@Cacheable中的value属性一样，key也会不一样。
     */
    @Bean
    public KeyGenerator keyGenerator() {
        LOGGER.debug("======Creating keyGenerator======");
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                StringBuilder sb = new StringBuilder();
                sb.append(o.getClass().getName())
                        .append(method.getName());
                for (Object obj : objects) {
                    sb.append(obj.toString());
                }
                LOGGER.debug("======keyGenerator=======" + sb.toString());
                return sb.toString();
            }
        };
    }
}