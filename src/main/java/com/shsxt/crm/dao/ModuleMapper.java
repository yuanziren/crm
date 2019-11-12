package com.shsxt.crm.dao;

import com.shsxt.crm.base.BaseDao;
import com.shsxt.crm.dto.ModuleDto;
import com.shsxt.crm.po.Module;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleMapper extends BaseDao<Module> {

    public List<ModuleDto> queryAllModuleByRoleId(Integer roleId);
}