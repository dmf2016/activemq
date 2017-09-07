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
public class TestListRemove {
	@Resource(name = "redisTemplate")
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * TODO 测试
	 * @Date:Sep 7, 2017
	 * @Author dmf
	 */
	@Test
	public void shoeLink() {
		// 添加 一个 list 列表
		ListOperations<String, Object> list = redisTemplate.opsForList();
		for (int i = 0; i < list.size("lpList"); i++) {
			String str = (String) list.index("lpList", i);
			System.out.println(str);
		}
	}
	/**
	 * TODO 测试删除
	 * @Date:Sep 7, 2017
	 * @Author dmf
	 */
	@Test
	public void deleteLink() {
		// 添加 一个 list 列表
		ListOperations<String, Object> list = redisTemplate.opsForList();
		for (int i = 0; i < list.size("lpList"); i++) {
			String str = (String) list.index("lpList", i);
			System.out.println("要删除的：" + str);
			list.remove("lpList", i, str);
		}
	}
	
	
}
