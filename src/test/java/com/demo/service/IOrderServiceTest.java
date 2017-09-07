package com.demo.service;

import static org.junit.Assert.*;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.demo.model.Orders;
import com.demo.service.IOrderService;


public class IOrderServiceTest {

    private ApplicationContext context;

    @Autowired
    private IOrderService orderService;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("spring/spring-context.xml");
        orderService = (IOrderService) context.getBean("orderService");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetOrderCount() {
        int count = orderService.getOrderCount();
        assertEquals(2, count);
    }

    @Test
    public void testInsert() {
        Orders orders = new Orders();
        orders.setCompanyId("test2");
        orders.setCharge(BigDecimal.valueOf(2.0));
        orders.setState(BigDecimal.valueOf(1));
        orders.setRemark("学生");
        orderService.insert(orders);
        System.out.println("--完成--");
    }

}
