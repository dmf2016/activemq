package com.demo.dao;

import com.demo.model.Orders;

public interface IOrderDao {
    public int getOrderCount();

    int insert(Orders record);
}
