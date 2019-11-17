package com.shsxt.crm.dao;

import com.shsxt.crm.base.BaseDao;
import com.shsxt.crm.dto.ModuleDto;
import com.shsxt.crm.po.Module;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ModuleMapper extends BaseDao<Module> {

    public List<ModuleDto> queryAllModuleByRoleId(Integer roleId);
    public List<Map> queryModuleByGrade(Integer grade);
    public Module queryModuleByName(String moduleName);
    public Module queryModuleByOptValue(String optValue);
    public Integer deleteModuleByOptValue(String optValue);
    public Integer queryTotalByOptValue(String optValue);
}