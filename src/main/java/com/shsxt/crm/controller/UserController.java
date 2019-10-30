package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.model.UserInfo;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    //http://localhost:8080/crm/user/login?userName=shsxt&userPwd=123
    @RequestMapping("login")
    @ResponseBody
    public ResultInfo login(String userName,String userPwd){
        UserInfo userInfo = userService.login(userName, userPwd);//捕获代码块ctrl+alt+t
        return success("登录成功",userInfo);
    }


    //http://localhost:8080/crm/user/updateUserPwd?oldPassword=1&newPassword=2&confirmPassword=2
    @RequestMapping("updateUserPwd")
    @ResponseBody
    public ResultInfo updateUserPwd(String oldPassword,
                                    String newPassword,
                                    String confirmPassword,
                                    HttpServletRequest request){
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        //获取用户Id
        userService.updateUserPwd(oldPassword,newPassword,confirmPassword,userId);
        return success("修改成功");
    }

    /**
     * 查询所有的客户经理
     * @return
     */
    @RequestMapping("queryCustomerManagers")
    @ResponseBody
    public List<Map> queryCustomerManagers(){
        return userService.queryCustomerManagers();
    }
}
