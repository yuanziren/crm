package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.dao.CustomerOrderMapper;
import com.shsxt.crm.po.CustomerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerOrderService extends BaseService<CustomerOrder> {
    @Autowired
    private CustomerOrderMapper customerOrderMapper;


}

