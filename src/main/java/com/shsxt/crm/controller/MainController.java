package com.shsxt.crm.controller;

import com.shsxt.crm.exceptions.ParamException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @RequestMapping("main")
    public String index(HttpServletRequest request){
        if (true) throw new ParamException("测试页面异常");
        request.setAttribute("ctx",request.getContextPath());
        return "main";
    }
}
