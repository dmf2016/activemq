package com.activemq.frist;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import org.springframework.jms.core.MessageCreator;

/**
 * 消息产生者
 * @Description 
 * @Project: spring-web
 * @Date:2016-8-24
 * @Author dmf
 * @Copyright (c) 2016, 33e9 All Rights Reserved.
 */
public class MyMessageCreator implements MessageCreator {
    public int n = 0;
    private static String str1 = "这个是第 ";
    private static String str2 = " 个测试消息！";
    private String str = "";

    public Message createMessage(Session paramSession) throws JMSException {
        System.out.println("MyMessageCreator  n=" + n);
        if (n == 9) {
            //在这个例子中表示第9次调用时，发送结束消息
            return paramSession.createTextMessage("end");
        }
        str = str1 + n + str2;
        return paramSession.createTextMessage(str);
    }
}
