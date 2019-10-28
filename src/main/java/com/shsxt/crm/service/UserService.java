package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.dao.UserMapper;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.model.UserInfo;
import com.shsxt.crm.po.User;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.utils.Md5Util;
import com.shsxt.crm.utils.UserIDBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserMapper userMapper;

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


}
