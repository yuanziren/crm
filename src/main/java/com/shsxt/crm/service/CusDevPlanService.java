package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.dao.CusDevPlanMapper;
import com.shsxt.crm.po.CusDevPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CusDevPlanService extends BaseService<CusDevPlan> {

    @Autowired
    private CusDevPlanMapper cusDevPlanMapper;


}
