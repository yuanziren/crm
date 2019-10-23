package com.shsxt.crm.dao;

import com.shsxt.crm.po.CusDev;

public interface CusDevMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CusDev record);

    int insertSelective(CusDev record);

    CusDev selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CusDev record);

    int updateByPrimaryKey(CusDev record);
}