package com.shsxt.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.dao.UserMapper;
import com.shsxt.crm.dao.UserRoleMapper;
import com.shsxt.crm.dto.UserDto;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.model.UserInfo;
import com.shsxt.crm.po.User;
import com.shsxt.crm.po.UserRole;
import com.shsxt.crm.query.UserQuery;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.utils.Md5Util;
import com.shsxt.crm.utils.UserIDBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class UserService extends BaseService<UserDto> {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 用户登录
     * @param userName
     * @param userPwd
     */
    public UserInfo login(String userName, String userPwd) {

        /**
         * 1、参数校验（非空判断）
         * 2、查询用户是否存在
         * 3、验证密码是否正确
         */

        ResultInfo info = new ResultInfo();
        AssertUtil.isTrue(StringUtils.isEmpty(userName), "用户名为空");
        AssertUtil.isTrue(StringUtils.isEmpty(userPwd), "密码为空");
        User user = userMapper.queryUserByName(userName);
        AssertUtil.isTrue(user == null, "用户不存在");

        /**
         * 后台是密文，前台是明文
         */
        AssertUtil.isTrue(!user.getUserPwd().equals(Md5Util.encode(userPwd)), "用户名或密码不正确");
        UserInfo userInfo = new UserInfo();
        userInfo.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        return userInfo;
    }

    /**
     * 修改密码
     * @param oldPassword
     * @param newPassword
     * @param confrimPassword
     */
    public void updateUserPwd(String oldPassword,
                              String newPassword,
                              String confrimPassword,
                              Integer userId
                              ){
        /**
         * 1.参数校验
         * 2.校验旧密码是否正确
         * 3.更改密码
         */
        checkupdateUserPwdParams(oldPassword,newPassword,confrimPassword);
        User user = userMapper.queryById(userId);
        AssertUtil.isTrue(user == null,"用户不存在或已注销");
        /**
         * 前台是明文，后台是密文
         */
        AssertUtil.isTrue(!user.getUserPwd().equals(Md5Util.encode(oldPassword)),"旧密码不正确");
        String encodePassword = Md5Util.encode(newPassword);
        AssertUtil.isTrue(userMapper.updateUserPwd(encodePassword,userId)<1,"密码修改失败");
    }

    private void checkupdateUserPwdParams(String oldPassword, String newPassword, String confrimPassword) {
        AssertUtil.isTrue(StringUtils.isEmpty(oldPassword),"旧密码为空");
        AssertUtil.isTrue(StringUtils.isEmpty(newPassword),"新密码为空");
        AssertUtil.isTrue(!newPassword.equals(confrimPassword),"两次密码输入不一致");
    }

    /**
     * 查询所有的客户经理
     * @return
     */
    public List<Map> queryCustomerManagers(){
        return userMapper.queryCustomerManagers();
    }


    public void saveOrUpdateUser(UserDto userDto){
        /***
         * 1. 校验参数
         * 2. 补全参数
         * 3. 区分添加和更新
         * 4. 执行操作
         * */
        checkUserParams(userDto);
        Integer id = userDto.getId();
        userDto.setUpdateDate(new Date());

        if (null == id){
            AssertUtil.isTrue(null!=userMapper.queryUserByName(userDto.getUserName()), "用户名已存在");
            /***
             * 设置默认密码: 123456
             * 注意: 加密
             * */
            userDto.setUserPwd(Md5Util.encode("123456"));
            userDto.setIsValid(1);
            userDto.setCreateDate(new Date());
            AssertUtil.isTrue(userMapper.save(userDto)<1, CrmConstant.OPS_FAILED_MSG);
        } else {
            /**
             * 用户更新用户名
             * 1. 通过id查询数据库名字
             * 2. 如果一致,则表示用户不修改用户名;
             * 3. 否则,用户在修改用户名,就需要做唯一校验
             * */

            String oldUserName = userMapper.queryById(id).getUserName();
            if(!oldUserName.equals(userDto.getUserName())){
                AssertUtil.isTrue(null!=userMapper.queryUserByName(userDto.getUserName()), "用户名已存在");
            }

            // 用户更新
            AssertUtil.isTrue(userMapper.update(userDto)<1, CrmConstant.OPS_FAILED_MSG);

            // 角色更新
            // 1. 删除角色
            Integer total = userRoleMapper.queryUserRoleByUserId(id);
            if(total>0){
                AssertUtil.isTrue(userRoleMapper.deleteUserRoleByUserId(id)<total, CrmConstant.OPS_FAILED_MSG);
            }

        }
        /***
         * 用户角色添加
         * */

        List<UserRole> userRoleList = new ArrayList<>();

        List<Integer> roleIds = userDto.getRoleIds();
        if(!CollectionUtils.isEmpty(roleIds)){
            for(Integer roleId : roleIds){
                UserRole userRole = new UserRole();
                userRole.setUserId(userDto.getId());// 设置用户id
                userRole.setRoleId(roleId);
                userRole.setCreateDate(new Date());
                userRole.setUpdateDate(new Date());
                userRoleList.add(userRole);
            }
            AssertUtil.isTrue(userRoleMapper.saveBatch(userRoleList)<userRoleList.size(),CrmConstant.OPS_FAILED_MSG);
        }


    }

    private void checkUserParams(UserDto userDto) {
        String userName = userDto.getUserName();
        AssertUtil.isTrue(StringUtils.isEmpty(userName), "用户名为空");
        AssertUtil.isTrue(StringUtils.isEmpty(userDto.getTrueName()), "真实姓名为空");
        AssertUtil.isTrue(StringUtils.isEmpty(userDto.getEmail()), "邮箱为空");
        AssertUtil.isTrue(StringUtils.isEmpty(userDto.getPhone()), "手机为空");
    }

    public Map<String,Object> queryForPage(UserQuery baseQuery) throws DataAccessException {
        PageHelper.startPage(baseQuery.getPageNum(),baseQuery.getPageSize());
        List<UserDto> entities=userMapper.queryByParams(baseQuery);
        PageInfo<UserDto> pageInfo=new PageInfo<UserDto>(entities);

        // 获取分页的集合,取到1,2,3 转换成 [1,2,3]
        List<UserDto> userDtoList = pageInfo.getList();
        for(UserDto userDto : userDtoList){
            String roleIdStr = userDto.getRoleIdStr();// 1,2,3
            if(null!=roleIdStr){
                String[] idArr = roleIdStr.split(",");
                List<Integer> roleIds = userDto.getRoleIds();
                for(String id : idArr){
                    roleIds.add(Integer.valueOf(id));
                }
            }
        }

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

}
