package com.shsxt.crm.utils;

import com.shsxt.crm.exceptions.ParamException;


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

}
