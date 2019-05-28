package com.utils.cache.redis;

import com.utils.cache.CacheManager;
import lombok.AllArgsConstructor;

/**
 * @ClassName: RedisHelper
 * @auther: gingerjie
 * @date: 2019/1/31 14:42
 * @Description: TODO
 * @Version 1.0.0
 */
@AllArgsConstructor
public class RedisHelper {
    /** 对外接口服务分区*/
    public static final String REGION_CHANNEL_INTERFACE = "c_interface";
    /** 理单服务分区*/
    public static final String REGION_ARRANGE = "arrange";
    /** 迪士尼会员服务分区 */
    public static final String REGION_ARRANGE_MEMBER = "member";
    /** 迪士尼配置分区 */
    public static final String REGION_SETTING= "setting";
    /** 迪士尼商品分区 */
    public static final String REGION_GOODS= "goods";
    /** 迪士尼基础数据分区 */
    public static final String REGION_BASE= " base";
    /** 迪士尼微信配置分区 */
    public static final String REGION_WX= "wx";
    //空缓存过期时间 2天后过期
    public final static int NULL_EXPIRE = 3600 * 24 * 2;

    private RedisClient client;
    private int expiration;

    /**
     * redisCacheManager生成类 方便每次调用都新生成对象 减少 出现并发时候ragion出现混乱情况
     * @param region
     * @return
     */
    public CacheManager buildCache(String region) {
        return new RedisCacheManager(client,expiration).region(region);
    }


}
