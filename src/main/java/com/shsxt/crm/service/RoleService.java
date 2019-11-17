package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.dao.ModuleMapper;
import com.shsxt.crm.dao.PermissionMapper;
import com.shsxt.crm.dao.RoleMapper;
import com.shsxt.crm.dao.UserRoleMapper;
import com.shsxt.crm.po.Permission;
import com.shsxt.crm.po.Role;
import com.shsxt.crm.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RoleService extends BaseService<Role> {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private ModuleMapper moduleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

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

    public void doGrant(Integer roleId, Integer[] moduleIds) {
        /***
         * 1. 先删除之前的权限
         * 2. 添加权限
         * */
        Integer total = permissionMapper.queryPermissionByRoleId(roleId);
        if (total > 0) {
            AssertUtil.isTrue(permissionMapper.deletePermissionByRoleId(roleId) < total, CrmConstant.OPS_FAILED_MSG);
        }

        if (null != moduleIds && moduleIds.length > 0) {
            List<Permission> permissionList = new ArrayList<>();

            for(Integer moduleId : moduleIds){
                Permission permission = new Permission();
                permission.setRoleId(roleId);
                permission.setModuleId(moduleId);
                permission.setCreateDate(new Date());
                permission.setUpdateDate(new Date());

                permission.setAclValue(moduleMapper.queryById(moduleId).getOptValue());

                permissionList.add(permission);
            }

            AssertUtil.isTrue(permissionMapper.saveBatch(permissionList)<permissionList.size(), CrmConstant.OPS_FAILED_MSG);
        }
    }

    public void deleteRole(Integer[] ids){
        if(null!=ids && ids.length>0){
            for(Integer id:ids){
                // 1. 删除角色
                AssertUtil.isTrue(roleMapper.delete(id)<1, CrmConstant.OPS_FAILED_MSG);
                // 2. 删除用户_角色
                Integer total01 = userRoleMapper.queryUserRoleByRoleId(id);
                if(total01>0){
                    AssertUtil.isTrue(userRoleMapper.deleteUserRoleByRoleId(id)<total01, CrmConstant.OPS_FAILED_MSG);
                }
                // 3. 删除权限(角色_模块)
                Integer total02 = permissionMapper.queryPermissionByRoleId(id);
                if (total02 > 0) {
                    AssertUtil.isTrue(permissionMapper.deletePermissionByRoleId(id) < total02, CrmConstant.OPS_FAILED_MSG);
                }
            }
        }
    }

}
