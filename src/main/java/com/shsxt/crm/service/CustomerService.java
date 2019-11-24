package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.dao.CustomerMapper;
import com.shsxt.crm.po.Customer;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.utils.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService extends BaseService<Customer> {

    @Autowired
    private CustomerMapper customerMapper;

    public List<Map> queryDataDicsByDicName(String dicName){
        return customerMapper.queryDataDicsByDicName(dicName);
    }

    public void saveOrUpdateCustomer(Customer customer){

        Integer id = customer.getId();
        customer.setUpdateDate(new Date());
        if(null==id){
            customer.setState(0);
            customer.setIsValid(1);
            customer.setCreateDate(new Date());
            // 客户编号
            customer.setKhno(MathUtil.genereateKhCode());
            AssertUtil.isTrue(customerMapper.save(customer)<1, CrmConstant.OPS_FAILED_MSG);
        }else{
            AssertUtil.isTrue(customerMapper.update(customer)<1, CrmConstant.OPS_FAILED_MSG);
        }
    }


}
