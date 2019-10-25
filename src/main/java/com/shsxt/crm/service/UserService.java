package com.shsxt.crm.service;

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
public class UserService {

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
}
