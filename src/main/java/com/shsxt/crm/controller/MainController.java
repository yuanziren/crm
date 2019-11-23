package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.dto.UserDto;
import com.shsxt.crm.exceptions.ParamException;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("main")
    public String index(HttpServletRequest request){
        /***
         * 查询权限列表
         * */
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        List<String> permissions = userService.queryPermissionsByUserId(userId);
        request.getSession().setAttribute(CrmConstant.USER_PERMISSIONS,permissions);
        System.out.println("================");
        System.out.println(permissions.toString());
        System.out.println("================");

        UserDto userDto = userService.queryById(userId);
        System.out.println(userDto);
        request.setAttribute("user", userDto);
        return "main";
    }
}
