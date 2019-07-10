package com.suneee.yangbo;

import com.utils.cache.redis.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@RestController
public class YangboApplication {

//	@Autowired
//	RedisHelper redisHelper ;

	private final static int  redisSearchKeywordLength = 19 ; // 需统计关键词的数量

	private final static  String preSearchkeyword = "preSearchkeyword_"; // 需统计关键词的key前缀

	public static void main(String[] args) {
		SpringApplication.run(YangboApplication.class, args);
	}

	@RequestMapping("/")
	public String hello(){
		ApplicationContext  applicationContext= new ClassPathXmlApplicationContext();
		applicationContext.getBean("com.abcd");
		updateLastRedisSearchKeyword(485003,"常用酒店");
		return "hello yangbo05527 world";
	}

	/**
	 * 更新用户最近搜索关键字
	 * @param memberId 会员Id
	 * @param keywords  搜索关键字
	 */
	private void updateLastRedisSearchKeyword(Integer memberId , String keywords ){
//		RedisHelper redisHelper = new RedisHelper();
//		int power = 0 ;  // 关键词的权值(Redis排序使用)
//		int skip = 1 ;
//		List<String> keywordMemberList = redisHelper.buildCache(RedisHelper.REGION_ARRANGE_MEMBER).getZset(preSearchkeyword+memberId, 0, -1);
//		redisHelper.buildCache(RedisHelper.REGION_ARRANGE_MEMBER).clearZset(preSearchkeyword+memberId,0,-1);
//		Collections.reverse(keywordMemberList);
//		if ( keywordMemberList!=null && keywordMemberList.size()<=redisSearchKeywordLength ){
//			skip = 0 ;
//		}
//		List<String> _keywordMemberList = keywordMemberList.stream().filter(  _keyword -> !_keyword.equals( keywords )  ) .skip(skip).limit(redisSearchKeywordLength).collect(Collectors.toList());
//		_keywordMemberList.add( keywords );
//		for (String _keywordMember:_keywordMemberList ) {
//			redisHelper.buildCache(RedisHelper.REGION_ARRANGE_MEMBER).zset(preSearchkeyword+memberId,_keywordMember,power);
//			power++;
//		}

	}

}
