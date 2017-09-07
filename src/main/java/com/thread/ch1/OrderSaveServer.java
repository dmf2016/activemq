package com.thread.ch1;

import java.math.BigDecimal;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.demo.model.Orders;
import com.demo.service.IOrderService;

/**
 * 保存信息
 * @Description 
 * @Project: activemq
 * @Date:2016-12-14
 * @Author dmf
 * @Copyright (c) 2016, 33e9 All Rights Reserved.
 */
public class OrderSaveServer implements Runnable {
    Logger logger = Logger.getLogger(OrderSaveServer.class.getSimpleName());
    private volatile boolean bStop = false; // 是否停止
    private ApplicationContext context;
    private IOrderService orderService;

    public OrderSaveServer() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        orderService = (IOrderService) context.getBean("orderService");
    }

    @Override
    public void run() {
        while (!bStop) {
            logger.info("--数据保存开始---");
            Orders orders = new Orders();
            orders.setCompanyId("test2");
            orders.setCharge(BigDecimal.valueOf(2.0));
            orders.setState(BigDecimal.valueOf(1));
            orders.setRemark("学生");
            orderService.insert(orders);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
