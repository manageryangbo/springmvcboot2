package com.utils.cache;




import com.utils.cache.redis.queue.Message;

import java.io.Serializable;
import java.util.List;

public interface CacheManager {
    <T> T get(final Object key, Class<T> clazz);

//    <T> Set<T> getAll(final String pattern, Class<T> clazz);

    void set(final Object key, final Serializable value, int seconds);

    void set(final Object key, final Serializable value);

    <T> List<T> getList(final Object key, Class<T> clazz);

//    <T> Set<T> getAll(final String pattern, Class<T> clazz);

    <T> void setList(final Object key, final List<T> value, int seconds);

    <T> void setList(final Object key, final List<T> value);

    Boolean exists(final Object key);

    void del(final Object key);

//    void delAll(final String pattern);

    String type(final Object key);

    Long expire(final Object key, final int seconds);

    Long expireAt(final Object key, final long unixTime);

    Long ttl(final Object key);

    <T> T getSet(final Object key, final Serializable value);


    void hset(Object key, Serializable field, Serializable value);

    <T> T  hget(Object key, Serializable field, Class<T> clazz);

    void hdel(Object key, Serializable field);

    Long setnx(Object key, Serializable value);

    Long incr(Object key);

    void setrange(Object key, long offset, String value);

    String getrange(Object key, long startOffset, long endOffset);

    <T> T get(Object key, Integer expire, Class<T> clazz);

    <T> T getFire(Object key, Class<T> clazz);

//    <T> Set<T> getAll(String pattern, Integer expire, Class<T> clazz);

    Object blpop(String messageType, int maxValue, Class<Message> messageClass);

    Long llen(String messageType);

    <T> Long rpush(Object key, T value, int i);

    void zset(Object key, Serializable value, int power);

    List<String> getZset(Object key, int start, int offest);

    void clearZset(Object key, int start, int offest);

    String setex(Object key, int expiresInSeconds, Serializable value);

}
