package com.shsxt.crm.dao;

import com.shsxt.crm.base.BaseDao;
import com.shsxt.crm.po.Permission;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionMapper extends BaseDao<Permission> {

    public Integer deletePermissionByRoleId(Integer roleId);
    public Integer queryPermissionByRoleId(Integer roleId);
}