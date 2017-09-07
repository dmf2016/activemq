package com.redis;

import java.util.Set;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * redis spring 简单例子
 * @author hk
 *
 * 2012-12-22 上午10:40:15
 */
public class TestRedis {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext app = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        //这里已经配置好,属于一个redis的服务接口
        RedisUtil redisService = (RedisUtil) app.getBean("redisUtil");

        redisService.set("username", "oyhk");//设值(查看了源代码,默认存活时间30分钟)
        String username = (String) redisService.get("username");//取值 
        System.out.println(username);

        Thread.sleep(2000);//我睡眠一会,再去取,这个时间超过了,他的存活时间
        String liveUsername1 = (String) redisService.get("username1");
        System.out.println(liveUsername1);//输出null

        //是否存在
        boolean exist = redisService.exists("username");
        System.out.println(exist);

        //删除
        redisService.set("username2", "oyhk2");
        String username2 = (String) redisService.get("username2");
        System.out.println(username2);

        redisService.remove("username2");
        String username2_2 = (String) redisService.get("username2");
        System.out.println(username2_2);//如果为null,那么就是删除数据了

    }
}
