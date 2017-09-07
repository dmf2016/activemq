package com.zjp.mq.service;

import static org.junit.Assert.*;
import java.util.Date;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.demo.service.IOrderService;
import com.zjp.mq.entity.QMessage;
import com.zjp.mq.utils.QMessageUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-context.xml" })
public class QMessageServiceTest {

    @Resource(name = "QMessageService")
    private QMessageService qMessageService;

    @Test
    public void testGetMessage() {
        //fail("Not yet implemented");
    }

    @Test
    public void testAddQMessage() {
        // fail("Not yet implemented");
        QMessage qMessage = new QMessage();
        //时间戳
        Date date = new Date();
        //生成消息Id
        String messageId = QMessageUtils.createMessageId(date);
        qMessage.setMessageId(messageId);
        qMessage.setBusinessMark("oop");
        qMessage.setMessageContent("你好，dmf。");
        qMessage.setStatus(Integer.valueOf(0));
        qMessage.setRetry(0);
        qMessageService.addQMessage(qMessage);

    }

    @Test
    public void testUpdateQMessage() {
        //fail("Not yet implemented");
    }

    @Test
    public void testDeleteQMessage() {
        //fail("Not yet implemented");
    }

    @Test
    public void testSelectAllQMessage() {
        //fail("Not yet implemented");
    }

}
