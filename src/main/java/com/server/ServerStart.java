package com.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.demo.service.IOrderService;
import com.thread.ThreadPool;
import com.thread.ch1.OrderSaveServer;

/**
 * 主线程
 * @Description 
 * @Project: activemq
 * @Date:2016-12-14
 * @Author dmf
 * @Copyright (c) 2016, 33e9 All Rights Reserved.
 */
public class ServerStart {

    private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    private IOrderService orderService = (IOrderService) context.getBean("orderService");

    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool();
        OrderSaveServer saveServer = new OrderSaveServer();//保存
        pool.start(saveServer);//启动
        pool.destroy();
    }

}
