package com.activemq.frist.consumer;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.JmsUtils;

/**
 * 消息接收方,当接收消息异常时处理
 * @Description 
 * @Project: activemq
 * @Date:2016-8-24
 * @Author dmf
 * @Copyright (c) 2016, 33e9 All Rights Reserved.
 */
public class MessageReciverThrow {
    private static final Log LOG = LogFactory.getLog(MessageReciver.class);
    private String[] configLocations;
    private ApplicationContext context;
    private JmsTemplate jmsTemplate;
    private Destination destination;

    public MessageReciverThrow() {
        configLocations = new String[] { "classpath:spring/spring-activemq.xml" };
        context = new ClassPathXmlApplicationContext(configLocations);
        jmsTemplate = (JmsTemplate) context.getBean("jmsTemplate");
        destination = (Destination) context.getBean("destination");
    }

    /**
     * 接收信息
     * TODO
     * @Date:2016-12-30
     * @Author dmf
     */
    public void reciver() {
        TextMessage msg = null;
        //是否继续接收消息
        boolean isContinue = true;
        while (isContinue) {
            msg = (TextMessage) jmsTemplate.receive(destination);
            try {
                System.out.println("收到消息 :" + msg.getText());
                if (msg.getText().equals("end")) {
                    isContinue = false;
                    System.out.println("收到退出消息，程序要退出！");
                }
                throw new JMSException("保存db出错");
            } catch (JMSException e) {
                isContinue = false;
                throw JmsUtils.convertJmsAccessException(e);
            }

        }

    }

    public static void main(String args[]) throws Exception {
        LOG.info("---接收信息程序已启动----");
        MessageReciverThrow reciver = new MessageReciverThrow();
        reciver.reciver();
        LOG.info("程序退出了！");
    }
}
