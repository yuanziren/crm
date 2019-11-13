package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.dto.ModuleDto;
import com.shsxt.crm.query.ModuleQuery;
import com.shsxt.crm.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("module")
public class ModuleController extends BaseController {

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

    @RequestMapping("queryModulesByParams")
    @ResponseBody
    public Map<String, Object> queryModulesByParams(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer rows,
            ModuleQuery query){
        query.setPageNum(page);
        query.setPageSize(rows);
        return moduleService.queryForPage(query);
    }

    //http://localhost:8080/crm/module/queryModuleByGrade?grade=2
    @RequestMapping("queryModuleByGrade")
    @ResponseBody
    public List<Map> queryModuleByGrade(Integer grade){
        return moduleService.queryModuleByGrade(grade);
    }


}
