package com.shsxt.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @RequestMapping("main")
    public String index(HttpServletRequest request){
        request.setAttribute("ctx",request.getContextPath());
        return "main";
    }
}
