package com.shsxt.crm.aop;

import com.shsxt.crm.annotations.RequestPermission;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.utils.AssertUtil;
import freemarker.ext.servlet.HttpSessionHashModel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by xlf on 2019/6/14.
 */
@Component
@Aspect
public class PermissionAdaptor {

    @Autowired
    private HttpSession session;

    @Pointcut("@annotation(com.shsxt.crm.annotations.RequestPermission)")
    public void cut(){}

    @Around("cut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {

        Object result = null;

        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        RequestPermission permission = method.getAnnotation(RequestPermission.class);
        String aclValue = permission.aclValue();// 权限码

        /***
         * 1. 获取权限码列表
         * 2. 判断列表中是否含有该权限码
         * */
        List<String> permissions = (List<String>) session.getAttribute(CrmConstant.USER_PERMISSIONS);

        AssertUtil.isTrue(CollectionUtils.isEmpty(permissions) || !permissions.contains(aclValue),"没有权限");

        result = pjp.proceed();

        return result;
    }
}
