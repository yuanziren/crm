package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.dao.SaleChanceMapper;
import com.shsxt.crm.dao.UserMapper;
import com.shsxt.crm.po.SaleChance;
import com.shsxt.crm.po.User;
import com.shsxt.crm.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class SaleChanceService extends BaseService<SaleChance> {

    @Autowired
    private SaleChanceMapper saleChanceMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 添加或更新SaleChance
     * @param saleChance
     */
    public void saveOrUpdateSaleChance(SaleChance saleChance,Integer userId){
        /**
         * 1.参数校验
         * 2.补全参数
         * 3.执行操作
         * 4.区分添加还是更新，通过id
         */
        AssertUtil.isTrue(StringUtils.isEmpty(saleChance.getCustomerName()),"客户名称为空");
        AssertUtil.isTrue(StringUtils.isEmpty(saleChance.getLinkMan()),"联系人为空");
        AssertUtil.isTrue(StringUtils.isEmpty(saleChance.getLinkPhone()),"联系电话为空");

        if (null != saleChance.getAssignMan()){
            saleChance.setAssignTime(new Date());// 分配时间
            saleChance.setState(1);// 已分配
        } else {
            saleChance.setState(0);// 未分配
        }
        saleChance.setUpdateDate(new Date());
        Integer id = saleChance.getId();
        if (null==id){
            //添加
            User user = userMapper.queryById(userId);
            saleChance.setCreateMan(user.getUserName());//创建人
            saleChance.setDevResult(0);//0 未开发
            saleChance.setCreateDate(new Date());
            AssertUtil.isTrue(saleChanceMapper.save(saleChance)<1,"添加失败");
        }else {
            //更新
            AssertUtil.isTrue(saleChanceMapper.update(saleChance)<1,"更新失败");
        }
    }


}
