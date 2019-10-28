package com.shsxt.crm.exceptions;

import com.alibaba.fastjson.JSON;
import com.shsxt.crm.model.ResultInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object target, Exception e) {

        ModelAndView mv = createDefaultModelAndView(request,e);
        /**
         *区分：json请求和普通页面请求
         * 通过 注解@ResponseBody
         * target 就是要访问的controller的方法
         */
//        System.out.println(target);

        if(target instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) target;
            Method method = handlerMethod.getMethod();
            ResponseBody responseBody = method.getAnnotation(ResponseBody.class);
            if (null == responseBody){
                // 代表访问的是一个普通页面请求
                ParamException ex = (ParamException) e;
                mv.addObject("errorMsg",ex.getMsg());// 设置错误信息
            } else {
                // 代表访问的是一个json请求
                ResultInfo info = new ResultInfo();
                info.setCode(300);
                info.setMsg("系统繁忙");

                if(e instanceof ParamException){
                    ParamException ex = (ParamException) e;
                    info.setMsg(ex.getMsg());
                }
                // 输出json数据
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/json;charset=utf-8");
                PrintWriter pw =null;

                try {
                    pw = response.getWriter();
                    pw.write(JSON.toJSONString(info));
                    pw.flush();
                    pw.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    if(null!=pw){
                        pw.close();
                    }
                }
            }
        }

        return mv;
    }

    private ModelAndView createDefaultModelAndView(HttpServletRequest request, Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");// 设置默认错误视图
        mv.addObject("errorMsg",e.getMessage());// 设置报错信息
        mv.addObject("ctx",request.getContextPath());// 设置项目路径
        mv.addObject("uri",request.getRequestURI());// 设置访问路径
        return mv;
    }
}
