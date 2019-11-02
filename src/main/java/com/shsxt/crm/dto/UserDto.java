package com.shsxt.crm.dto;

import com.shsxt.crm.po.User;

import java.util.ArrayList;
import java.util.List;

public class UserDto extends User {

    private String roleName;
    private String roleIdStr;
    private List<Integer> roleIds = new ArrayList<>();

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }

    public String getRoleIdStr() {
        return roleIdStr;
    }

    public void setRoleIdStr(String roleIdStr) {
        this.roleIdStr = roleIdStr;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "roleName='" + roleName + '\'' +
                ", roleIdStr='" + roleIdStr + '\'' +
                ", roleIds=" + roleIds +
                '}';
    }
}
