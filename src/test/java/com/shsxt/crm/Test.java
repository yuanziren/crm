package com.shsxt.crm;


import com.shsxt.crm.controller.UserController;
import com.shsxt.crm.dao.RoleMapper;
import com.shsxt.crm.query.UserQuery;
import com.shsxt.crm.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:mybatis.xml",
        "classpath:spring.xml",
        "classpath:servlet-context.xml" })
public class Test {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserController userController;

    @org.junit.Test
    public void test01(){
        System.out.println(roleMapper.queryAllRoles());
    }

    @org.junit.Test
    public void test02(){
        UserQuery query = new UserQuery();
        query.setPageNum(1);
        query.setPageSize(10);
        System.out.println(userController.queryUsersByParams(1,10,query));
    }
}
