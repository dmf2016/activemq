package com.redis;

import java.util.Iterator;

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
	 * 从右边增加 TODO
	 * @Date:Sep 7, 2017
	 * @Author dmf
	 */
	@Test
	public void addRightLink() {
		// 添加 一个 list 列表
		ListOperations<String, Object> list = redisTemplate.opsForList();
		list.rightPush("lpList", "1");
		list.rightPush("lpList", "2");
		for (int i = 0; i < list.size("lpList"); i++) {
			String str = (String) list.index("lpList", i);
			System.out.println(str);
		}
	}
	/**
	 * 从左边增加 TODO
	 * @Date:Sep 7, 2017
	 * @Author dmf
	 */
	@Test
	public void addLeftLink() {
		// 添加 一个 list 列表
		ListOperations<String, Object> list = redisTemplate.opsForList();
		list.leftPush("lpList", "a");
		list.leftPush("lpList", "b");
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
		// 输出 list
		for (int i = 0; i < list.size("lpList"); i++) {
			String str = (String) list.index("lpList", i);
			System.out.println("-----1--:" + str);
		}
		Iterator it = (Iterator) list.rightPop("lpList");
		while (it.hasNext()) {
			String str = (String) it.next();
			System.out.println("-----2--:" + str);
			// 出队列
			list.leftPop("lpList");
		}
		// 再显示队列数据
		for (int i = 0; i < list.size("lpList"); i++) {
			String str = (String) list.index("lpList", i);
			System.out.println("-----3--:" + str);
		}
	}
}
