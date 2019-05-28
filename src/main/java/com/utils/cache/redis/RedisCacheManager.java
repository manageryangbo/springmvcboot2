package com.utils.cache.redis;

import com.utils.cache.CacheManager;
import com.utils.cache.redis.queue.Message;
import com.utils.cache.util.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.BinaryJedisCommands;
import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Redis缓存辅助类
 *
 * @author gingerjie
 * @version 2016年4月2日 下午4:17:22
 */
@Slf4j
public final class RedisCacheManager implements CacheManager {
    /**这个参数我们填的是NX，意思是SET IF NOT EXIST，即当key不存在时，我们进行set操作；若key已经存在，则不做任何操作；*/
    private static final byte[] SET_IF_NOT_EXIST = "NX".getBytes();
    /**这个参数我们传的是PX，意思是我们要给这个key加一个过期的设置，具体时间由第五个参数决定。*/
    private static final byte[] SET_WITH_EXPIRE_TIME = "PX".getBytes();

    private static final Long RELEASE_SUCCESS = 1L;
    private static final String LOCK_SUCCESS = "OK";

    private RedisClient client;
    private String namespace;
    private String region;
    private byte[] regionBytes;
    private int expiration;

    private final String SUBFIX = ":";

    public RedisCacheManager(){

    }

    public RedisCacheManager(RedisClient client, int expiration) {
        this.client = client;
        this.expiration = expiration;
//        CacheUtil.setCacheManager(this);
    }

    public String genKey(Object... keysVal){
        return StringUtils.join(keysVal,SUBFIX);
    }
    /**
     * 缓存构造
     * @param namespace 命名空间，用于在多个实例中避免 key 的重叠
     * @param region 缓存区域的名称
     * @param client 缓存客户端接口
     */
    public RedisCacheManager(String namespace, String region, RedisClient client, int expiration) {
        if(region == null || region.trim().length() == 0){
            // 缺省region
            this.region = "_";
        }
        this.client = client;
        this.namespace = namespace;
        this.regionBytes = _regionName(region).getBytes();
        this.expiration = expiration;
    }
    public CacheManager region(String region){
        if(region == null || region.trim().length() == 0){
            // 缺省region
            this.region = "_";
        }
        else {
            this.region = region;
        }
        return this;
    }
    /**
     * 在region里增加一个可选的层级,作为命名空间,使结构更加清晰
     * 同时满足小型应用,多个项目共享一个redis database的场景
     *
     * @param region
     * @return
     */
    private String _regionName(String region) {
        if (namespace != null && !namespace.trim().isEmpty())
            region = namespace + ":" + region;
        return region;
    }

    private byte[] _key(Object key) {
        try {
            return (this.region + ":" + key).getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            return (this.region + ":" + key).getBytes();
        }
    }
    abstract class Executor<T> {
        RedisClient client;

        public Executor(RedisClient client) {
            this.client = client;
        }
        /**
         * 回调
         * @return 执行结果
         */
        abstract T execute();

        /**
         * 调用{@link #execute()}并返回执行结果
         * 它保证在执行{@link #execute()}之后释放数据源returnResource(jedis)
         * @return 执行结果
         */
        public T getResult() {
            T result = null;
            try {
                result = execute();
            } catch (Throwable e) {
                log.error("Redis execute exception",e);
                throw new RuntimeException("Redis execute exception", e);
            } finally {
                if (client != null) {
                    client.release();
                }
            }
            return result;
        }
    }

    @Override
    public final <T> T get(final Object key,Class<T> clazz) {
        return new Executor<T>(client) {
            @Override
            T execute() {
                return ConvertUtil.unserialize(client.get().get(_key(key)),clazz);
            }
        }.getResult();
    }

    @Override
    public final <T> T get(final Object key, Integer expire,Class<T> clazz) {
        expire(key, expire);
        return new Executor<T>(client) {
            @Override
            T execute() {
                return ConvertUtil.unserialize(client.get().get(_key(key)),clazz);
            }
        }.getResult();
    }

    @Override
    public final <T> T  getFire(final Object key,Class<T> clazz) {
        expire(key, expiration);
        return new Executor< T>(client) {
            @Override
            T execute() {
                return ConvertUtil.unserialize(client.get().get(_key(key)),clazz);
            }
        }.getResult();
    }

//    @Override
//    public final <T> Set<T> getAll(final String pattern,Class<T> clazz) {
//        Set<T> values = InstanceUtil.newHashSet();
//        try {
//            BinaryJedisCommands cmd = client.get();
//            if (cmd instanceof Jedis) {
//                Collection<String> keys = ((Jedis) cmd).keys(pattern);//keys这个功能集群不提供 消耗很大
//                for (Object key : keys) {
//                    values.add(ConvertUtil.unserialize(cmd.get(_key(key)),clazz));
//                }
//            }
//        } catch (Exception var9) {
//            log.info("redis队列hget失败：" + var9.getMessage());
//        }  finally {
//            if (client != null) {
//                client.release();
//            }
//        }
//
//        return values;
//    }

//    @Override
//    public final <T> Set<T> getAll(final String pattern, Integer expire,Class<T> clazz) {
//
//        Set<T> values = InstanceUtil.newHashSet();
//        try {
//            BinaryJedisCommands cmd = client.get();
//            if (cmd instanceof Jedis) {
//                Collection<String> keys = ((Jedis) cmd).keys(pattern);
//                for (Object key : keys) {
//                    expire(key, expire);
//                    values.add(ConvertUtil.unserialize(cmd.get(_key(key)),clazz));
//                }
//            }
//        } catch (Exception var9) {
//            log.info("redis队列hget失败：" + var9.getMessage());
//        }  finally {
//            if (client != null) {
//                client.release();
//            }
//        }
//
//        return values;
//    }

    /**
     * 获取数据
     * @param messageType
     * @param waitSeconds
     * @param messageClass
     * @return
     */
    @Override
    public Object blpop(String messageType, int waitSeconds, Class<Message> messageClass) {
        try {
            BinaryJedisCommands cmd = client.get();
            //如果使用的集群的需要添加额外判断
            if (cmd instanceof Jedis) {
                List<byte[]> values = ((Jedis) cmd).brpop(waitSeconds, messageType.getBytes());

                if (values != null && values.size() > 0)
                {
                    byte[] value = values.get(1);
                    return ConvertUtil.unserialize(value, messageClass);
                }
            }
        } catch (Exception var9) {
            log.info("redis队列blpop失败：" + var9.getMessage());
        }  finally {
            if (client != null) {
                client.release();
            }
        }
        return null;
    }

    @Override
    public Long llen(String messageType) {
        try {
            BinaryJedisCommands cmd = client.get();
            Long result =  cmd.llen(messageType.getBytes());
            return result;
        } catch (Exception var9) {
            log.info("redis队列llen失败：" + var9.getMessage());
        }  finally {
            if (client != null) {
                client.release();
            }
        }
        return null;
    }

    @Override
    public  <T> Long rpush(Object key, T value, int second) {
        try {
            BinaryJedisCommands cmd = client.get();
            byte[] bytes = ConvertUtil.serialize(value);
            Long result =  cmd.rpush(key.toString().getBytes(), bytes);
            if (second > 0)
            {
                cmd.expire(key.toString().getBytes(), second);
            }
            return result;
        } catch (Exception var9) {
            log.info("redis队列rpush失败：" + var9.getMessage());
        }  finally {
            if (client != null) {
                client.release();
            }
        }
        return null;
    }

    @Override
    public void zset(Object key, Serializable value,int power) {
        new Executor<Long>(client) {
            @Override
            Long execute() {
                byte[] bytes = ConvertUtil.serialize(value);
                double core = power;
                Long zadd = client.get().zadd(key.toString().getBytes(), core, bytes);
                return zadd;
            }
        }.getResult();
    }

    @Override
    public List<String> getZset(Object key, int start,int offest) {
        return new Executor<List<String>>(client) {
            @Override
            List<String> execute() {
                Set<byte[]> zrevrange = client.get().zrevrange(key.toString().getBytes(), start, offest);
                Iterator<byte[]> iteratorSet = zrevrange.iterator();
                List<String> result = new ArrayList<>();
                while (iteratorSet.hasNext()){
                    String next = ConvertUtil.unserialize( iteratorSet.next(),String.class );
                    result.add( next );
                    System.out.println("result:================"+next);
                }
//                client.get().zremrangeByRank(key.toString().getBytes(),0,0);
                return result;
            }
        }.getResult();
    }

    @Override
    public void clearZset(Object key, int start,int offest) {
        new Executor<Long>(client) {
            @Override
            Long execute() {
                Long aLong = client.get().zremrangeByRank(key.toString().getBytes(), start, offest);
                return aLong ;
            }
        }.getResult();
    }


    @Override
    public final void set(final Object key, final Serializable value, int seconds) {
        new Executor<String>(client) {
            @Override
            String execute() {
                byte[] valueByte = ConvertUtil.serialize(value);
                String result = client.get().set(_key(key),valueByte);
                return result;
            }
        }.getResult();
        if(seconds >0) {
         expire(key, seconds);
        }
    }

    @Override
    public final void set(final Object key, final Serializable value) {
        new Executor<String>(client) {
            @Override
            String execute() {
                byte[] valueByte = ConvertUtil.serialize(value);
                String result = client.get().set(_key(key),valueByte);
                return result;
            }
        }.getResult();
        expire(key, expiration);
    }

    @Override
    public <T> List<T> getList(Object key, Class<T> clazz) {

        try {
            byte[] bytes = client.get().get(_key(key));
            if(bytes!=null) {
                return ConvertUtil.unserializeList(bytes,clazz);
            }
        } catch (Exception var9) {
            log.info("redis队列get List失败：" + var9.getMessage());
        }  finally {
            if (client != null) {
                client.release();
            }
        }
        return null;
    }

    @Override
    public <T> void setList(Object key, List<T> value, int seconds) {
        if(CollectionUtils.isNotEmpty(value)) {
            new Executor<String>(client) {
                @Override
                String execute() {
                    byte[] valueByte = ConvertUtil.serializeList(value);
                    String result = client.get().set(_key(key), valueByte);
                    return result;
                }
            }.getResult();
            expire(key, seconds);
        }
    }

    @Override
    public <T> void setList(Object key, List<T> value) {
        if(CollectionUtils.isNotEmpty(value)) {
            new Executor<String>(client) {
                @Override
                String execute() {
                    byte[] valueByte = ConvertUtil.serializeList(value);
                    String result = client.get().set(_key(key),valueByte);
                    return result;
                }
            }.getResult();
        }
    }



    @Override
    public final Boolean exists(final Object key) {
        return new Executor<Boolean>(client) {
            @Override
            Boolean execute() {
                return client.get().exists(_key(key));
            }
        }.getResult();
    }

    @Override
    public final void del(final Object key) {
        new Executor<Long>(client) {
            @Override
            Long execute() {
               return client.get().del(_key(key));
            }
        }.getResult();
    }

//    @Override
//    public final void delAll(final String likeKey) {
//        new Executor<Long>(client) {
//            @Override
//            Long execute() {
//                BinaryJedisCommands cmd = client.get();
//                if (cmd instanceof BinaryJedis) {
//                    Set<byte[]> keys = ((BinaryJedis) cmd).keys(new String(likeKey + "*").getBytes());
//                    byte[][] bytes = Arrays.stream(keys.toArray()).map(k -> _key(k.toString())).toArray(byte[][]::new);
//                    return ((BinaryJedis)cmd).del(bytes);
//                }
//                return 0L;
//            }
//        }.getResult();
//    }

    @Override
    public final String type(final Object key) {
        return new Executor<String>(client) {
            @Override
            String execute() {
                return client.get().type(_key(key));
            }
        }.getResult();
    }

    /**
     * 在某段时间后失效
     * @return
     */
    @Override
    public final Long expire(final Object key, final int seconds) {
        return new Executor<Long>(client) {
            @Override
            Long execute() {
                return  client.get().expire(_key(key),seconds);
            }
        }.getResult();
    }

    /**
     * 在某个时间点失效
     * @param key
     * @param unixTime
     * @return
     */
    @Override
    public final Long expireAt(final Object key, final long unixTime) {
        return new Executor<Long>(client) {
            @Override
            Long execute() {
                return  client.get().expireAt(_key(key),unixTime);
            }
        }.getResult();
    }

    @Override
    public final Long ttl(final Object key) {
        return new Executor<Long>(client) {
            @Override
            Long execute() {
                return  client.get().ttl(_key(key));
            }
        }.getResult();
    }

    @Override
    public final void setrange(final Object key, final long offset, final String value) {
          new Executor<Long>(client) {
            @Override
            Long execute() {
                byte[] valueByte = ConvertUtil.serialize(value);
                return client.get().setrange(_key(key),offset,valueByte);
            }
        }.getResult();
    }

    @Override
    public final String getrange(final Object key, final long startOffset, final long endOffset) {
        return  new Executor<String>(client) {
            @Override
            String execute() {
                byte[] result = client.get().getrange(_key(key),startOffset,endOffset);
                return  ConvertUtil.unserialize(result,String.class);
            }
        }.getResult();
    }

    @Override
    public final Object getSet(final Object key, final Serializable value) {
        return  new Executor<Object>(client) {
            @Override
            Object execute() {
                byte[] valueByte = ConvertUtil.serialize(value);
                return client.get().getSet(_key(key),valueByte);
            }
        }.getResult();
    }

    @Override
    public Long setnx(Object key, Serializable value) {
       return  new Executor<Long>(client) {
            @Override
            Long execute() {
                byte[] valueByte = ConvertUtil.serialize(value);
                return client.get().setnx(_key(key),valueByte);
            }
        }.getResult();
    }
    @Override
    public String setex(Object key,int expiresInSeconds, Serializable value) {
        return  new Executor<String>(client) {
            @Override
            String execute() {
                byte[] valueByte = ConvertUtil.serialize(value);
                return client.get().setex(_key(key),expiresInSeconds,valueByte);
            }
        }.getResult();
    }


    @Override
    public void hset(Object key, Serializable field, Serializable value) {
         new Executor<Long>(client) {
            @Override
            Long execute() {
                byte[] fieldByte = ConvertUtil.serialize(field);
                byte[] valueByte = ConvertUtil.serialize(value);
                return   client.get().hset(_key(key),fieldByte,valueByte);
            }
        }.getResult();
    }

    @Override
    public <T> T hget(Object key, Serializable field,Class<T> clazz) {
        return new Executor<T>(client) {
            @Override
            T execute() {
                byte[] fieldByte = ConvertUtil.serialize(field);
                return ConvertUtil.unserialize(client.get().hget(_key(key),fieldByte),clazz);
            }
        }.getResult();
    }

    @Override
    public void hdel(Object key, Serializable field) {
         new Executor<Long>(client) {
            @Override
            Long execute() {
                byte[] fieldByte = ConvertUtil.serialize(field);
                return client.get().hdel(_key(key),fieldByte);
            }
        }.getResult();
    }

    @Override
    public Long incr(Object key) {
        return new Executor<Long>(client) {
            @Override
            Long execute() {
                return  client.get().incr(_key(key));
            }
        }.getResult();
    }

    /**
     * 尝试获取分布式锁
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public  boolean tryGetDistributedLock(String lockKey, String requestId, int expireTime) {
        try {
            byte[] lockKeyByte = ConvertUtil.serialize(lockKey);
            byte[] requestIdByte = ConvertUtil.serialize(requestId);

            String result = client.get().set(lockKeyByte, requestIdByte,SET_IF_NOT_EXIST , SET_WITH_EXPIRE_TIME, expireTime);

            if (LOCK_SUCCESS.equals(result)) {
                return true;
            }
            return false;
        } catch (Exception var9) {
            log.info("redis队列tryGetDistributedLock失败：" + var9.getMessage());
        }  finally {
            if (client != null) {
                client.release();
            }
        }
        return false;
    }
    /**
     * 释放分布式锁
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public  boolean releaseDistributedLock(String lockKey, String requestId) {
        try {
            //Lua代码
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            Object result = null;
            if(client.get() instanceof Jedis) {
                ((Jedis)client.get()).eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
            }
//            else if(client.get() instanceof JedisCluster) {
//                ((JedisCluster)client.get()).eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
//            }
            if (RELEASE_SUCCESS.equals(result)) {
                return true;
            }
        } catch (Exception var9) {
            log.info("redis队列releaseDistributedLock失败：" + var9.getMessage());
        }  finally {
            if (client != null) {
                client.release();
            }
        }
        return false;

    }
    // 未完，待续...
}
