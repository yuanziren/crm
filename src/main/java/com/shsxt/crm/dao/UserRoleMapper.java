package com.shsxt.crm.dao;

import com.shsxt.crm.base.BaseDao;
import com.shsxt.crm.po.UserRole;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRoleMapper extends BaseDao<UserRole> {

    public Integer deleteUserRoleByUserId(Integer userId);
    public Integer queryUserRoleByUserId(Integer userId);
    public Integer deleteUserRoleByRoleId(Integer roleId);
    public Integer queryUserRoleByRoleId(Integer roleId);
}