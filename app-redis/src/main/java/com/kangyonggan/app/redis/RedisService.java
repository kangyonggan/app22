package com.kangyonggan.app.redis;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis服务接口
 *
 * @author kangyonggan
 * @since 5/5/18
 */
public interface RedisService {

    /**
     * set
     *
     * @param key   键
     * @param value 值
     */
    void set(String key, Object value);

    /**
     * set
     *
     * @param key     键
     * @param value   值
     * @param timeout 过期时间(秒)
     */
    void set(String key, Object value, long timeout);

    /**
     * set
     *
     * @param key     键
     * @param value   值
     * @param timeout 过期时间
     * @param unit    过期时间单位
     */
    void set(String key, Object value, long timeout, TimeUnit unit);

    /**
     * get
     *
     * @param key 键
     * @return 返回值
     */
    Object get(String key);

    /**
     * multiGet
     *
     * @param keys 一批键
     * @return 返回一批值
     */
    List<Object> multiGet(Set<String> keys);

    /**
     * delete
     *
     * @param key 键
     * @return 值
     */
    Object delete(String key);

    /**
     * delete all like pattern
     *
     * @param pattern 表达式
     */
    void deleteAll(String pattern);

    /**
     * incr
     *
     * @param key 键
     * @return 返回自增后的值
     */
    long incr(String key);

    /**
     * listLeftPush
     *
     * @param key 键
     * @param url 值
     * @return 集合大小
     */
    long listLeftPush(String key, String url);

    /**
     * listRightPush
     *
     * @param key 键
     * @param url 值
     * @return 集合大小
     */
    long listRightPush(String key, String url);

    /**
     * listRange
     *
     * @param key   键
     * @param start 开始下标
     * @param end   结束下标
     * @return 返回范围内的集合
     */
    List<Object> listRange(String key, long start, long end);

    /**
     * hashSetNx
     *
     * @param hash  哈希值
     * @param key   键
     * @param value 值
     * @return 成功返回true，否则返回false
     */
    boolean hashSetNx(String hash, String key, String value);

    /**
     * hashSize
     *
     * @param hash 哈希值
     * @return 返回哈希大小
     */
    long hashSize(String hash);

    /**
     * hashExist
     *
     * @param hash 哈希值
     * @param key  键
     * @return 存在返回true，否则返回false
     */
    boolean hashExist(String hash, String key);

    /**
     * get keys by pattern
     *
     * @param pattern 表达式
     * @return 返回符合条件的一批键
     */
    Set<String> getKeys(String pattern);

}
