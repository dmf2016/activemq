package com.demo.service;

import com.demo.model.Orders;

public interface IOrderService {
    public int getOrderCount();
    
    /**
     * 保存
     * TODO
     * @param record
     * @return
     * @Date:2016-12-1
     * @Author dmf
     */
    public int insert(Orders record);
}
