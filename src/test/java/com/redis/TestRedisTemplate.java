package com.redis;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * spring redis 测试
 * @Description
 * @Project: activemq
 * @Date:Sep 7, 2017
 * @Author dmf
 * @Copyright (c) 2017, 33e9 All Rights Reserved.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-context.xml")
public class TestRedisTemplate {
	@Resource(name = "redisTemplate")
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * 从左边增加 TODO
	 * @Date:Sep 7, 2017
	 * @Author dmf
	 */
	@Test
	public void addLeftLink() {
		// 添加 一个 list 列表
		ListOperations<String, Object> list = redisTemplate.opsForList();
		for (int i = 0; i < 10; i++) {
			list.leftPush("lpList", "a" + i);
		}
		// 输出 list
		for (int i = 0; i < list.size("lpList"); i++) {
			String str = (String) list.index("lpList", i);
			System.out.println(str);
		}
	}
	/**
	 * 出队列 TODO
	 * @Date:Sep 7, 2017
	 * @Author dmf
	 */
	@Test
	public void popLeftLink() {
		// 添加 一个 list 列表
		ListOperations<String, Object> list = redisTemplate.opsForList();
		String key = "lpList";
		// 输出 list
		for (int i = 0; i < list.size(key); i++) {
			String str = (String) list.index(key, i);
			System.out.println("-----1--:" + str);
		}
		System.out.println("------------OUT---------------");
		long length = list.size(key);
		for (long i = 0; i < length; i++) {
			String uid = (String) list.rightPop(key);
			System.out.println(uid);
		}
		// 再显示队列数据
		System.out.println("-----再显示队列数据--:");
		for (int i = 0; i < list.size(key); i++) {
			String str = (String) list.index(key, i);
			System.out.println("-----3--:" + str);
		}
	}
}
