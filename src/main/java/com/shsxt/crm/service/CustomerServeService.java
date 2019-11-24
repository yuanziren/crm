package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.dao.CustomerServeMapper;
import com.shsxt.crm.dao.UserMapper;
import com.shsxt.crm.po.CustomerServe;
import com.shsxt.crm.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomerServeService extends BaseService<CustomerServe> {

    @Autowired
    private CustomerServeMapper customerServeMapper;

    @Autowired
    private UserMapper userMapper;

    public void saveOrUpdateCustomerServe(CustomerServe customerServe, Integer userId){
        Integer id = customerServe.getId();
        customerServe.setUpdateDate(new Date());

        if(null==id){
            customerServe.setState("1");
            customerServe.setCreatePeople(userMapper.queryById(userId).getUserName());
            customerServe.setIsValid(1);
            customerServe.setCreateDate(new Date());
            AssertUtil.isTrue(customerServeMapper.save(customerServe)<1, CrmConstant.OPS_FAILED_MSG);
        }else{

            String state = customerServe.getState();// 获取状态 1 2 3 4 5
            if(state.equals("1")){
                customerServe.setState("2");
                customerServe.setAssignTime(new Date());
            } else if(state.equals("2")){
                customerServe.setState("3");
                customerServe.setServiceProcePeople(userMapper.queryById(userId).getUserName());
                customerServe.setServiceProceTime(new Date());
            } else if(state.equals("3")){
                customerServe.setState("4");
            }

            AssertUtil.isTrue(customerServeMapper.update(customerServe)<1, CrmConstant.OPS_FAILED_MSG);
        }
    }
}
