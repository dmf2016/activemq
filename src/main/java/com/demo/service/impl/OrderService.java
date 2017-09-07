package com.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.dao.IOrderDao;
import com.demo.model.Orders;
import com.demo.service.IOrderService;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private IOrderDao orderDao;

    @Override
    public int getOrderCount() {
        return orderDao.getOrderCount();
    }

    @Override
    public int insert(Orders record) {
        return orderDao.insert(record);
    }

}
