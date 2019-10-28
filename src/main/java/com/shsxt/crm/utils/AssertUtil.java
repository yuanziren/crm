package com.shsxt.crm.utils;

import com.shsxt.crm.exceptions.ParamException;

import javax.security.auth.login.LoginException;


public class AssertUtil {

    public static void isTrue(boolean flag, String msg){
        if(flag){
            // 抛异常
            throw new ParamException(msg);
        }
    }

    public static void isTrue(boolean flag,Integer code, String msg){
        if(flag){
            // 抛异常
            throw new ParamException(code, msg);
        }
    }

    public static void isNotLogin(boolean flag, String msg) throws LoginException {
        if(flag){
            // 抛异常
            throw new LoginException(msg);
        }
    }
}
