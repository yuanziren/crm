package com.shsxt.crm.dao;

import com.shsxt.crm.base.BaseDao;
import com.shsxt.crm.po.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseDao<User> {

    public User queryUserByName(String userName);
    public Integer updateUserPwd(@Param("userPwd") String userPwd, @Param("id") Integer id);
}