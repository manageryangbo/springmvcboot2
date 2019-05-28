package com.utils.cache.redis.queue;

import com.utils.cache.redis.RedisHelper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 消息总线，用于向消息总线存入消息
 *
 * @author gingerjie
 * @version 2019年3月2日 下午4:17:22
 */
public class MessageTrunk
{
	private RedisHelper redisHelper;

//	@Autowired
//	@Qualifier(value = "messageTrunktaskExecutor")
	private ThreadPoolTaskExecutor threadPool;


	/**
	 * 失败重试次数，超过此值则不再重试，默认3次
	 */
	private int failRetryTimes = 3;

	/**
	 * 如果线程池满了，生产者暂停的时间，单位：S
	 */
	private int threadPoolFullSleepSeconds = 1;

	public MessageTrunk(){

	}

	public MessageTrunk(RedisHelper redisHelper, ThreadPoolTaskExecutor threadPool) {
		this.redisHelper = redisHelper;
		this.threadPool = threadPool;
	}

	public ThreadPoolTaskExecutor getThreadPool()
	{
		return threadPool;
	}

	public void setThreadPool(ThreadPoolTaskExecutor threadPool)
	{
		this.threadPool = threadPool;
	}

	public int getFailRetryTimes()
	{
		return failRetryTimes;
	}

	public void setFailRetryTimes(int failRetryTimes)
	{
		this.failRetryTimes = failRetryTimes;
	}

	public int getThreadPoolFullSleepSeconds()
	{
		return threadPoolFullSleepSeconds;
	}

	public void setThreadPoolFullSleepSeconds(int threadPoolFullSleepSeconds)
	{
		this.threadPoolFullSleepSeconds = threadPoolFullSleepSeconds;
	}

	/**
	 * 推送消息
	 * 
	 * @param message
	 */
	public void put(Message message)
	{
		redisHelper.buildCache(AbstarctMessageHandler.MESSAGE_REGION).rpush(message.getKey(), message, 0);
	}
}
