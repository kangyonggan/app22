package com.kangyonggan.app.handle;

import com.kangyonggan.app.redis.RedisService;
import com.kangyonggan.app.util.SpringUtils;
import com.kangyonggan.extra.core.exception.MethodCalledFrequencyException;
import com.kangyonggan.extra.core.handle.FrequencyHandle;
import lombok.extern.log4j.Log4j2;

/**
 * 限制方法调用频率，基于redis
 *
 * @author kangyonggan
 * @since 5/5/18
 */
@Log4j2
public class RedisFrequencyHandle implements FrequencyHandle {

    /**
     * 方法调用频率限制
     *
     * @param key       键
     * @param interval  间隔
     * @param interrupt 是否中断
     */
    @Override
    public void limit(String key, long interval, boolean interrupt) {
        boolean isLimited = this.isLimited(key, interval);
        if (isLimited) {
            String msg = String.format("Method %s called frequency during %sms times.", key, interval);
            log.warn(msg);
            if (interrupt) {
                throw new MethodCalledFrequencyException(msg);
            }
        }

    }

    /**
     * 判断是否限制
     *
     * @param key      键
     * @param interval 间隔
     * @return 限制返回true，否则返回false
     */
    private synchronized boolean isLimited(String key, Long interval) {
        Long lastTime = this.getLastTime(key);
        Long currentTime = System.currentTimeMillis();
        if (interval > currentTime - lastTime) {
            return true;
        } else {
            SpringUtils.getBean(RedisService.class).set(key, currentTime);
            return false;
        }
    }

    /**
     * 获取最后一次调用的时间
     *
     * @param key 键
     * @return 返回最后一次调用的时间，如果从未调用就返回0
     */
    private Long getLastTime(String key) {
        RedisService redisService = SpringUtils.getBean(RedisService.class);
        Object lastTime = redisService.get(key);
        if (lastTime == null) {
            lastTime = 0L;
            redisService.set(key, lastTime);
        }

        return (Long) lastTime;
    }

}
