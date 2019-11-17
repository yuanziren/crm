package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.dao.ModuleMapper;
import com.shsxt.crm.dao.PermissionMapper;
import com.shsxt.crm.dto.ModuleDto;
import com.shsxt.crm.po.Module;
import com.shsxt.crm.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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


    public void saveOrUpdateModule(Module module){
        checkModuleParams(module);
        module.setUpdateDate(new Date());
        Integer id = module.getId();
        if(null==id){
            module.setIsValid((byte) 1);
            module.setCreateDate(new Date());

            AssertUtil.isTrue(moduleMapper.save(module)<1, CrmConstant.OPS_FAILED_MSG);
        }else{

        }
    }

    private void checkModuleParams(Module module) {

        /***
         * 1. 唯一校验: 模块名称 和 权限码
         * 2. 权限码 格式
         * */

        // 唯一校验: 模块名称
        AssertUtil.isTrue(null!=moduleMapper.queryModuleByName(module.getModuleName()), "模块名称已存在");

        // 权限码 格式: 位数 和 前缀
        // 位数: 0 2位 1 4位 2 6位
        Integer grade = module.getGrade();// 层级
        String optValue = module.getOptValue();// 权限码
        int len = (grade + 1) * 2;
        AssertUtil.isTrue(optValue.length()!=len, "权限码位数不正确,应该为"+len+"位");

        if(grade!=0){
            // 前缀:
            // 10   1001 √ 2001 ×
            // 1010 101001 102001
            Integer parentId = module.getParentId();
            Module parentModule = moduleMapper.queryById(parentId);
            String parentOptValue = parentModule.getOptValue();

            AssertUtil.isTrue(!optValue.startsWith(parentOptValue),"权限码格式不正确,应该为"+parentOptValue+"xx");
        }else{
            module.setParentId(null);// s设置父id为空
        }

        AssertUtil.isTrue(null!=moduleMapper.queryModuleByOptValue(optValue),"权限码已存在");

    }

    public void deleteModule(Integer id){
        // 1. 级联删除当前模块
        Module module = moduleMapper.queryById(id);
        String optValue = module.getOptValue();
        Integer total = moduleMapper.queryTotalByOptValue(optValue);
        if(total>0)
            AssertUtil.isTrue(moduleMapper.deleteModuleByOptValue(module.getOptValue())<total, CrmConstant.OPS_FAILED_MSG);

        // 2. 级联删除关联权限
        Integer total02 = permissionMapper.queryPermissionByOptValue(optValue);
        if(total02>0)
            AssertUtil.isTrue(permissionMapper.deletePermissionByOptValue(optValue)<total02,CrmConstant.OPS_FAILED_MSG);

    }




}
