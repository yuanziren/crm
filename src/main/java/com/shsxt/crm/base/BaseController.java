package com.shsxt.crm.base;

import com.shsxt.crm.model.ResultInfo;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xlf on 2019/6/5.
 */
public class BaseController {


    public ResultInfo success(Integer code, String msg, Object result){
        ResultInfo info = new ResultInfo();
        info.setCode(code);
        info.setMsg(msg);
        info.setResult(result);
        return info;
    }

    public ResultInfo success(String msg, Object result){
        ResultInfo info = new ResultInfo();
        info.setCode(200);
        info.setMsg(msg);
        info.setResult(result);
        return info;
    }

    public ResultInfo success(String msg){
        ResultInfo info = new ResultInfo();
        info.setCode(200);
        info.setMsg(msg);
        return info;
    }

    @ModelAttribute
    public void preMethod(HttpServletRequest request){
        request.setAttribute("ctx", request.getContextPath());
    }
}
