package com.demo.insertTest;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.demo.model.Orders;
import com.demo.service.IOrderService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.Order;

import javax.swing.text.html.HTMLDocument;

import static oracle.net.aso.C00.i;

/**
 * 测试保存
 *
 * @Description
 * @Project: activemq
 * @Date:2016-12-9
 * @Author dmf
 * @Copyright (c) 2016, 33e9 All Rights Reserved.
 */
public class SingeSave {

    public ApplicationContext context;
    public IOrderService orderService;

    public SingeSave() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        orderService = (IOrderService) context.getBean("orderService");
    }

    public void testGetOrderCount() {
        int count = orderService.getOrderCount();
    }


    private Orders testInsert(int nums) {
        Orders orders = new Orders();
        orders.setCompanyId("test" + nums);
        orders.setCharge(BigDecimal.valueOf(2.0));
        orders.setState(BigDecimal.valueOf(1));
        orders.setRemark("我是一个学生，在班里" + nums);
        return orders;
    }

    /**
     * 批量保存
     *
     * @param num
     */
    public void bathchInsert(int num) {
        long begintime = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            this.testInsert(num);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("保存用时：" + (endTime - begintime) / 1000 + "毫秒。");
    }

    public static void main(String[] args) {
        SingeSave singe = new SingeSave();
        Orders orders;
        //保存10000条数据
        ConcurrentLinkedQueue<Orders> queue = new ConcurrentLinkedQueue<Orders>();
        for (int i = 0; i < 10000; i++) {
            orders = singe.testInsert(i);
            queue.add(orders);
        }
        // System.out.println("orders:"+queue.size());
        long begintime;
        begintime = System.currentTimeMillis();
        Iterator it  = queue.iterator();
        while (it.hasNext()) {
            orders = (Orders) it.next();
            singe.orderService.insert(orders);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("保存用时：" + (endTime - begintime) / 1000 + "秒。");

    }

}
