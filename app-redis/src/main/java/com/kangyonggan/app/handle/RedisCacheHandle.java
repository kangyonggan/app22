package com.kangyonggan.app.handle;

import com.kangyonggan.app.redis.RedisService;
import com.kangyonggan.app.util.SpringUtils;
import com.kangyonggan.extra.core.handle.CacheHandle;

import java.util.concurrent.TimeUnit;

/**
 * 基于redis的缓存
 *
 * @author kangyonggan
 * @since 5/5/18
 */
public class RedisCacheHandle implements CacheHandle {

    /**
     * redis服务
     */
    private RedisService redisService;

    @Override
    public Object set(String key, Object returnValue, Long expire) {
        getRedisService().set(key, returnValue, expire, TimeUnit.MILLISECONDS);
        return returnValue;
    }

    @Override
    public Object get(String key) {
        return getRedisService().get(key);
    }

    @Override
    public void delete(String... keys) {
        for (String key : keys) {
            getRedisService().delete(key);
        }
    }

    private RedisService getRedisService() {
        if (redisService == null) {
            redisService = SpringUtils.getBean(RedisService.class);
        }

        return redisService;
    }
}
