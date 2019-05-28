package com.utils.cache.redis.queue;/**
 * Created by dingxiangyong on 2017/3/12.
 */

import com.utils.cache.redis.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * 消息监听器
 * @author gingerjie
 * @version 2019年3月2日 下午4:17:22
 **/
@Service
@ConditionalOnProperty(prefix = "disney.redis",name = "cluster-name",havingValue = "wastons")
public class MessageMonitor {
    @Autowired
    private RedisHelper redisHelper;

    /**
     * 获取待处理消息个数
     * @param messageType
     * @return
     */
    public int getMessageLeft(String messageType){
        return redisHelper.buildCache(AbstarctMessageHandler.MESSAGE_REGION).llen(messageType).intValue();
    }
}
