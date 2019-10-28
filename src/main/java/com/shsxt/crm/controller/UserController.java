package com.shsxt.crm.controller;

import com.shsxt.crm.exceptions.ParamException;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.model.UserInfo;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    //http://localhost:8080/crm/user/login?userName=shsxt&userPwd=123
    @RequestMapping("login")
    @ResponseBody
    public ResultInfo login(String userName,String userPwd){
        ResultInfo info = new ResultInfo();
        try {
            UserInfo userInfo = userService.login(userName, userPwd);//捕获代码块ctrl+alt+t
            info.setResult(userInfo);
            info.setCode(200);
            info.setMsg("登录成功");
        } catch (ParamException e) {
            info.setCode(300);
            info.setMsg(e.getMsg());
        } catch (Exception e) {
            info.setCode(300);
            info.setMsg(e.getMessage());
        }

        return info;
    }


    //http://localhost:8080/crm/user/updateUserPwd?oldPassword=1&newPassword=2&confrimPassword=2
    @RequestMapping("updateUserPwd")
    @ResponseBody
    public ResultInfo updateUserPwd(String oldPassword,
                                    String newPassword,
                                    String confirmPassword,
                                    HttpServletRequest request){
        ResultInfo info = new ResultInfo();
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);

        //获取用户Id
        try {
            userService.updateUserPwd(oldPassword,newPassword,confirmPassword,userId);
            info.setCode(200);
            info.setMsg("修改成功");
        } catch (ParamException e) {
            info.setCode(300);
            info.setMsg(e.getMsg());
            e.printStackTrace();
        } catch (Exception e) {
            info.setCode(300);
            info.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return info;
    }
}
