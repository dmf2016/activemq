package com.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.demo.service.IOrderService;

public class MyBatisTest {

    public static void main(String[] args) {
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        IOrderService orderService = (IOrderService) context.getBean("orderService");
        int count = orderService.getOrderCount();
        System.out.println("count:" + count);

    }

}
