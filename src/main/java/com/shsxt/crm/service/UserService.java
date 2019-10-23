package com.shsxt.crm.service;

import com.shsxt.crm.dao.UserMapper;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.po.User;
import com.shsxt.crm.utils.Md5Util;
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
    public ResultInfo login(String userName,String userPwd){

        /**
         * 1、参数校验（非空判断）
         * 2、查询用户是否存在
         * 3、验证密码是否正确
         */

        ResultInfo info = new ResultInfo();
        if (StringUtils.isEmpty(userName)){
            info.setCode(300);
            info.setMsg("用户名为空");
            return info;
        }

        if (StringUtils.isEmpty(userPwd)){
            info.setCode(300);
            info.setMsg("密码为空");
            return info;
        }

        User user = userMapper.queryUserByName(userName);
        if (user==null){
            info.setCode(300);
            info.setMsg("用户不存在");
            return info;
        }

        if (user.getUserPwd().equals(Md5Util.encode(userPwd))){
            info.setCode(200);
            info.setMsg("登录成功");
            return info;
        }else {
            info.setCode(300);
            info.setMsg("用户名或密码不正确");
            return info;
        }
    }
}
