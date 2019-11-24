package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.dao.CustomerLossMapper;
import com.shsxt.crm.dao.CustomerMapper;
import com.shsxt.crm.po.Customer;
import com.shsxt.crm.po.CustomerLoss;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.utils.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService extends BaseService<Customer> {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerLossMapper customerLossMapper;

    public void addLossCustomer(){
        List<Customer> customerList = customerMapper.queryLossCustomer();
        if(customerList.size()>0){
            List<CustomerLoss> customerLossList = new ArrayList<>();
            for(Customer customer : customerList){
                CustomerLoss customerLoss = new CustomerLoss();
                customerLoss.setCusManager(customer.getCusManager());
                customerLoss.setCusName(customer.getName());
                customerLoss.setCusNo(customer.getKhno());
                customerLoss.setIsValid(1);
                customerLoss.setState(0);// 0 预流失
                customerLoss.setCreateDate(new Date());
                customerLoss.setUpdateDate(new Date());
                customerLossList.add(customerLoss);

                // 更改客户状态
                customer.setState(1);
                AssertUtil.isTrue(customerMapper.update(customer)<1,CrmConstant.OPS_FAILED_MSG);
            }

            AssertUtil.isTrue(customerLossMapper.saveBatch(customerLossList)<customerLossList.size(),CrmConstant.OPS_FAILED_MSG);
        }
    }

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
