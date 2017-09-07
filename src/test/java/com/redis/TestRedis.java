package com.redis;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

/**
 * redis spring 简单例子
 * @Description
 * @Project: activemq
 * @Date:Sep 7, 2017
 * @Author dmf
 * @Copyright (c) 2017, 33e9 All Rights Reserved.
 */
public class TestRedis {
	@Test
	public void testSpringRedis() throws InterruptedException {
		ApplicationContext app = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
		// 这里已经配置好,属于一个redis的服务接口
		RedisUtil redisService = (RedisUtil) app.getBean("redisUtil");
		redisService.set("username", "oyhk");// 设值(查看了源代码,默认存活时间30分钟)
		String username = (String) redisService.get("username");// 取值
		System.out.println(username);
		Thread.sleep(2000);// 我睡眠一会,再去取,这个时间超过了,他的存活时间
		String liveUsername1 = (String) redisService.get("username1");
		System.out.println(liveUsername1);// 输出null
		// 是否存在
		boolean exist = redisService.exists("username");
		System.out.println(exist);
		// 删除
		redisService.set("username2", "oyhk2");
		String username2 = (String) redisService.get("username2");
		System.out.println(username2);
		redisService.remove("username2");
		String username2_2 = (String) redisService.get("username2");
		System.out.println(username2_2);// 如果为null,那么就是删除数据了
	}
	/**
	 * spirng redis template TODO
	 * @Date:Sep 7, 2017
	 * @Author dmf
	 */
	@Test
	public void testSpringTemplate() {
		ApplicationContext app = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
		final RedisTemplate<String, Object> redisTemplate = app.getBean("redisTemplate", RedisTemplate.class);
		// 添加一个 key
		ValueOperations<String, Object> value = redisTemplate.opsForValue();
		value.set("lp", "hello word");
		// 获取 这个 key 的值
		System.out.println(value.get("lp"));
		// 添加 一个 hash集合
		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "lp");
		map.put("age", "26");
		hash.putAll("lpMap", map);
		// 获取 map
		System.out.println(hash.entries("lpMap"));
		// 添加 一个 list 列表
		ListOperations<String, Object> list = redisTemplate.opsForList();
		list.rightPush("lpList", "lp");
		list.rightPush("lpList", "26");
		// 输出 list
		System.out.println(list.range("lpList", 0, 1));
		// 添加 一个 set 集合
		SetOperations<String, Object> set = redisTemplate.opsForSet();
		set.add("lpSet", "lp");
		set.add("lpSet", "26");
		set.add("lpSet", "178cm");
		// 输出 set 集合
		System.out.println(set.members("lpSet"));
		// 添加有序的 set 集合
		ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
		zset.add("lpZset", "lp", 0);
		zset.add("lpZset", "26", 1);
		zset.add("lpZset", "178cm", 2);
		// 输出有序 set 集合
		System.out.println(zset.rangeByScore("lpZset", 0, 2));
	}
}
