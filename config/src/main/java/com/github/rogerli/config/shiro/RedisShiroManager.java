package com.github.rogerli.config.shiro;

import com.github.rogerli.utils.SerializeStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Set;

@Component
public class RedisShiroManager {

    @Autowired
    private RedisTemplate redisTemplate;

    public RedisShiroManager() {

    }

    public RedisShiroManager(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * get value from redis
     *
     * @param key
     * @return
     */
    public byte[] get(final byte[] key) {
        return (byte[]) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                return connection.get(key);
            }

        });
    }

    /**
     * get value from redis
     *
     * @param key
     * @return
     */
    public String get(final String key) {
        return (String) SerializeStringUtils.deserialize(SerializeStringUtils.serialize(key));
    }

    /**
     * @param serialize
     * @return
     */
    public Set<byte[]> getByPattern(final byte[] serialize) {
        return (Set<byte[]>) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                return connection.get(serialize);
            }
        });

    }

    /**
     * set
     *
     * @param key
     * @param value
     * @return
     */
    public byte[] set(final byte[] key, final byte[] value) {
        return (byte[]) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.set(key, value);
                return value;
            }
        });
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @return
     */

    public String set(final String key, final String value) {
        return (String) SerializeStringUtils.deserialize(set(SerializeStringUtils.serialize(key), SerializeStringUtils.serialize(value)));
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public byte[] set(final byte[] key, final byte[] value, final int expire) {
        return (byte[]) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.set(key, value);
                if (expire != 0) {
                    connection.expire(key, expire);
                }
                return value;
            }
        });
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public String set(final String key, final String value, final int expire) {
        return (String) SerializeStringUtils.deserialize(set(SerializeStringUtils.serialize(key), SerializeStringUtils.serialize(value), expire));
    }

    /**
     * del
     *
     * @param key
     */
    public void del(final byte[] key) {
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                return connection.del(key);
            }
        });
    }

    /**
     * del
     *
     * @param key
     */
    public void del(final String key) {
        del(SerializeStringUtils.serialize(key));
    }

    /**
     * flush
     */
    public void flushDB() {
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.flushDb();
                return connection;
            }
        });
    }

    /**
     * size
     */
    public Long dbSize() {
        return (Long) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                return connection.dbSize();
            }
        });
    }

    /**
     * keys
     *
     * @param pattern
     * @return
     */
    public Set<byte[]> keys(final String pattern) {
        return (Set<byte[]>) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                return connection.keys(SerializeStringUtils.serialize(pattern));
            }
        });
    }

    public void dels(final byte[] keysPattern) {
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                Set<byte[]> keys = connection.keys(keysPattern);
                Iterator<byte[]> ito = keys.iterator();
                while (ito.hasNext()) {
                    connection.del(ito.next());
                }
                return connection;
            }
        });
    }

    public void dels(final String pattern) {
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                Set<byte[]> keys = connection.keys(SerializeStringUtils
                        .serialize(pattern));
                Iterator<byte[]> ito = keys.iterator();
                while (ito.hasNext()) {
                    connection.del(ito.next());
                }
                return connection;
            }
        });
    }

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

}