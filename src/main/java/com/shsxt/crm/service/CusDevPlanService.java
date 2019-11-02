package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.dao.CusDevPlanMapper;
import com.shsxt.crm.po.CusDevPlan;
import com.shsxt.crm.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class CusDevPlanService extends BaseService<CusDevPlan> {

    @Autowired
    private CusDevPlanMapper cusDevPlanMapper;

    public void saveOrUpdateCusDevPlan(CusDevPlan cusDevPlan){
        /***
         * 1. 校验参数
         * 2. 区分添加还是更新
         * 3. 补全参数
         * 4. 执行操作
         * */
        AssertUtil.isTrue(StringUtils.isEmpty(cusDevPlan.getPlanItem()),"计划内容为空");
        AssertUtil.isTrue(null == cusDevPlan.getPlanDate(), "计划时间为空");
        AssertUtil.isTrue(StringUtils.isEmpty(cusDevPlan.getExeAffect()), "执行效果为空");

        Integer id = cusDevPlan.getId();

        cusDevPlan.setUpdateDate(new Date());

        if(null == id){
            // 添加
            cusDevPlan.setCreateDate(new Date());
            cusDevPlan.setIsValid(1);
            AssertUtil.isTrue(cusDevPlanMapper.save(cusDevPlan)<1, CrmConstant.OPS_FAILED_MSG);
        }else{
            // 更新
            AssertUtil.isTrue(cusDevPlanMapper.update(cusDevPlan)<1, CrmConstant.OPS_FAILED_MSG);
        }

    }


}
