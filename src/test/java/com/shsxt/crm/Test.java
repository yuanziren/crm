package com.shsxt.crm;


import com.shsxt.crm.dao.RoleMapper;
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

    @org.junit.Test
    public void test01(){
        System.out.println(roleMapper.queryAllRoles());
    }
}
