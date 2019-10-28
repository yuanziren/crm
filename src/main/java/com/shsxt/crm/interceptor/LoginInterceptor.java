package com.shsxt.crm.interceptor;


import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {


    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /***
         * 登陆验证
         * 1. 获取cookie,从request获取
         * 2. 判断cookie中的加密字符串是否为空以及是否有效
         * */
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        AssertUtil.isNotLogin(null==userId || null==userService.queryById(userId),"用户未登录或不存在");
        return true;
    }
}
