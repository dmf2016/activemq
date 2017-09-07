package com.activemq.frist.producer;

import javax.jms.Destination;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import com.activemq.frist.MyMessageCreator;

/**
 * 发送消息方
 * @Description 
 * @Project: activemq
 * @Date:2016-8-24
 * @Author dmf
 * @Copyright (c) 2016, 33e9 All Rights Reserved.
 */
public class MessageSender extends Thread {
    public static void main(String args[]) throws Exception {
        String[] configLocations = new String[] { "classpath:spring/spring-activemq.xml" };
        ApplicationContext context = new ClassPathXmlApplicationContext(configLocations);
        JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsTemplate");
        Destination destination = (Destination) context.getBean("destination");
        for (int i = 1; i < 100; i++) {
            System.out.println("发送 i=" + i);
            //消息产生者
            MyMessageCreator myMessageCreator = new MyMessageCreator();
            myMessageCreator.n = i;
            jmsTemplate.send(destination, myMessageCreator);
            sleep(1000);//10秒后发送下一条消息
        }
    }
}
