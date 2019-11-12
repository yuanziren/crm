package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.dao.RoleMapper;
import com.shsxt.crm.po.Role;
import com.shsxt.crm.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RoleService extends BaseService<Role> {

    @Autowired
    private RoleMapper roleMapper;

    public List<Map> queryAllRoles(){
        return roleMapper.queryAllRoles();
    }

    public void saveOrUpdateRole(Role role) {
        /***
         * 1. 角色名唯一判断
         * */
        AssertUtil.isTrue(null != roleMapper.queryRoleByName(role.getRoleName()), "角色名称已存在");

        Integer id = role.getId();
        role.setUpdateDate(new Date());
        if (null == id) {
            role.setIsValid(1);
            role.setCreateDate(new Date());
            AssertUtil.isTrue(roleMapper.save(role) < 1, CrmConstant
                    .OPS_FAILED_MSG);
        } else {
            role.setRoleName(null);     //后台限制角色名称不允许修改
            AssertUtil.isTrue(roleMapper.update(role) < 1, CrmConstant.OPS_FAILED_MSG);
        }
    }
}
