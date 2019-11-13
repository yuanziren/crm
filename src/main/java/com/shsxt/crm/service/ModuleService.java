package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.dao.ModuleMapper;
import com.shsxt.crm.dao.PermissionMapper;
import com.shsxt.crm.dto.ModuleDto;
import com.shsxt.crm.po.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ModuleService extends BaseService<Module> {

    @Autowired
    private ModuleMapper moduleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    public List<ModuleDto> queryAllModuleByRoleId(Integer roleId){
        return moduleMapper.queryAllModuleByRoleId(roleId);
    }

    public List<Map> queryModuleByGrade(Integer grade){
        return moduleMapper.queryModuleByGrade(grade);
    }





}
