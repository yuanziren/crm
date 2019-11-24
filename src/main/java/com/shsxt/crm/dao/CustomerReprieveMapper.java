package com.shsxt.crm.dao;

import com.shsxt.crm.po.CustomerReprieve;

public interface CustomerReprieveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerReprieve record);

    int insertSelective(CustomerReprieve record);

    CustomerReprieve selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerReprieve record);

    int updateByPrimaryKey(CustomerReprieve record);
}