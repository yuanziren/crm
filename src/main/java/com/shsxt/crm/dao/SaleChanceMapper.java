package com.shsxt.crm.dao;

import com.shsxt.crm.base.BaseDao;
import com.shsxt.crm.po.SaleChance;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleChanceMapper extends BaseDao<SaleChance> {


    public Integer updateDevResult(SaleChance saleChance);

}