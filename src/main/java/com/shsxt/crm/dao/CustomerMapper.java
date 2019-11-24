package com.shsxt.crm.dao;

import com.shsxt.crm.base.BaseDao;
import com.shsxt.crm.po.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CustomerMapper extends BaseDao<Customer> {
    public List<Map> queryDataDicsByDicName(String dicName);
    public List<Customer> queryLossCustomer();
}