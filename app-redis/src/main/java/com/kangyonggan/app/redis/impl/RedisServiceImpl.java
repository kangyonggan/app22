package com.kangyonggan.app.redis.impl;

import com.kangyonggan.app.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author kangyonggan
 * @since 5/5/18
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, Object value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    @Override
    public void set(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public List<Object> multiGet(Set<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    @Override
    public Object delete(String key) {
        Object object = redisTemplate.opsForValue().get(key);
        if (object != null) {
            redisTemplate.delete(key);
        }
        return object;
    }

    @Override
    public void deleteAll(String pattern) {
        redisTemplate.delete(redisTemplate.keys(pattern));
    }

    @Override
    public long incr(String key) {
        return redisTemplate.opsForValue().increment(key, 1);
    }

    @Override
    public long listLeftPush(String key, String url) {
        return redisTemplate.opsForList().leftPush(key, url);
    }

    @Override
    public long listRightPush(String key, String url) {
        return redisTemplate.opsForList().rightPush(key, url);
    }

    @Override
    public List<Object> listRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    @Override
    public boolean hashSetNx(String hash, String key, String value) {
        return redisTemplate.opsForHash().putIfAbsent(hash, key, value);
    }

    @Override
    public long hashSize(String hash) {
        return redisTemplate.opsForHash().size(hash);
    }

    @Override
    public boolean hashExist(String hash, String key) {
        return redisTemplate.opsForHash().hasKey(hash, key);
    }

    @Override
    public Set<String> getKeys(String pattern) {
        return redisTemplate.keys(pattern);
    }
}
