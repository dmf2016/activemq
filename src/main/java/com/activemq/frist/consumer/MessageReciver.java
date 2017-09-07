package com.activemq.frist.consumer;

import javax.jms.Destination;
import javax.jms.TextMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;


/**
 * 消息接收方
 * @Description 
 * @Project: activemq
 * @Date:2016-8-24
 * @Author dmf
 * @Copyright (c) 2016, 33e9 All Rights Reserved.
 */
public class MessageReciver {
    private static final Log LOG = LogFactory.getLog(MessageReciver.class);

    public static void main(String args[]) throws Exception {
        String[] configLocations = new String[] { "classpath:spring/spring-activemq.xml" };
        ApplicationContext context = new ClassPathXmlApplicationContext(configLocations);
        JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsTemplate");
        Destination destination = (Destination) context.getBean("destination");
        LOG.info("---接收信息程序已启动----");
        TextMessage msg = null;
        //是否继续接收消息
        boolean isContinue = true;
        while (isContinue) {
            msg = (TextMessage) jmsTemplate.receive(destination);
            System.out.println("收到消息 :" + msg.getText());
            if (msg.getText().equals("end")) {
                isContinue = false;
                System.out.println("收到退出消息，程序要退出！");
            }
        }
        LOG.info("程序退出了！");
    }
}
