package com.shsxt.crm.controller;

import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    //http://localhost:8080/crm/user/login?userName=shsxt&userPwd=123
    @RequestMapping("login")
    @ResponseBody
    public ResultInfo login(String userName,String userPwd){
        return userService.login(userName,userPwd);
    }
}
