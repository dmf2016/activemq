package com.zjp.mq.handle;

/**
 * Module Desc:com.zjp.mq.handle
 * User: zjprevenge
 * Date: 2016/8/27
 * Time: 18:31
 */

public interface TxMessageCallback {

    /**
     * 事务消息处理成功后的处理
     *
     * @param messageId
     */
    void onSuccess(String messageId);

    /**
     * 事务消息处理失败后的处理
     *
     * @param e
     * @param messageId
     */
    void onFail(Exception e, String messageId);
}
