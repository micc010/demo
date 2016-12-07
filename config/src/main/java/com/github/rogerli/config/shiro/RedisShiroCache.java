package com.github.rogerli.config.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.mybatis.caches.redis.SerializeUtil;

import java.util.*;

public class RedisShiroCache<K, V> implements Cache<K, V> {

    public static final String REDIS_SHIRO_CACHE = "shiro-cache:";

    private RedisShiroManager redisShiroManager;

    private String name;

    public RedisShiroCache(String name, RedisShiroManager redisShiroManager) {
        this.name = name;
        this.redisShiroManager = redisShiroManager;
    }

    /**
     * 自定义relm中的授权/认证的类名加上授权/认证英文名字
     *
     * @return
     */
    public String getName() {
        if (name == null)
            return "";
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public V get(K key) throws CacheException {
        byte[] byteKey = SerializeUtil.serialize(getCacheKey(key));
        byte[] byteValue = redisShiroManager.get(byteKey);
        return (V) SerializeUtil.unserialize(byteValue);
    }

    @Override
    public V put(K key, V value) throws CacheException {
        V previos = get(key);
        redisShiroManager.set(SerializeUtil.serialize(getCacheKey(key)),
                SerializeUtil.serialize(value));
        return previos;
    }

    @Override
    public V remove(K key) throws CacheException {
        V previos = get(key);
        redisShiroManager.del(SerializeUtil.serialize(getCacheKey(key)));
        return previos;
    }

    @Override
    public void clear() throws CacheException {
        byte[] keysPattern = SerializeUtil.serialize(REDIS_SHIRO_CACHE
                + "*");
        redisShiroManager.dels(keysPattern);
    }

    @Override
    public int size() {
        Set<K> keys = keys();
        if (keys == null || keys.size() == 0)
            return 0;
        return keys().size();
    }

    @Override
    public Set<K> keys() {
        Set<byte[]> byteSet = redisShiroManager.getByPattern(SerializeUtil
                .serialize(REDIS_SHIRO_CACHE + "*"));
        Set<K> keys = new HashSet<K>();
        for (byte[] bs : byteSet) {
            keys.add((K) SerializeUtil.unserialize(bs));
        }
        return keys;
    }

    @Override
    public Collection<V> values() {
        Set<byte[]> byteSet = redisShiroManager.getByPattern(SerializeUtil
                .serialize(REDIS_SHIRO_CACHE + "*"));
        List<V> result = new LinkedList<V>();
        for (byte[] bs : byteSet) {
            result.add((V) SerializeUtil.unserialize(redisShiroManager
                    .get(bs)));
        }
        return result;
    }

    private String getCacheKey(Object key) {
        return REDIS_SHIRO_CACHE + getName() + ":" + key;
    }
}