package com.shsxt.crm.controller;

import com.shsxt.crm.dto.ModuleDto;
import com.shsxt.crm.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @RequestMapping("index")
    public String index(){
        return "module";
    }

    @RequestMapping("queryAllModuleByRoleId")
    @ResponseBody
    public List<ModuleDto> queryAllModuleByRoleId(Integer roleId){
        return moduleService.queryAllModuleByRoleId(roleId);
    }
}
