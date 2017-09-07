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

	@Test
	public void addLink() {
		// 添加 一个 list 列表
		ListOperations<String, Object> list = redisTemplate.opsForList();
		list.rightPush("lpList", "lp");
		list.rightPush("lpList", "26");
		// 输出 list
		System.out.println(list.range("lpList", 0, 1));
	}
}
