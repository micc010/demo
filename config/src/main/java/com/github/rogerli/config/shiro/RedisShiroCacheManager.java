package com.github.rogerli.config.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedisShiroCacheManager implements CacheManager {

    @Autowired
    private RedisShiroManager redisShiroManager;

    public RedisShiroCacheManager() {

    }

    /**
     *
     * @param redisShiroManager
     */
    public RedisShiroCacheManager(RedisShiroManager redisShiroManager) {
        this.redisShiroManager = redisShiroManager;
    }

    /**
     *
     * @param name
     * @param <K>
     * @param <V>
     * @return
     */
    @Override
    public <K, V> Cache<K, V> getCache(String name) {
        return new RedisShiroCache<K, V>(name, redisShiroManager);
    }

}  