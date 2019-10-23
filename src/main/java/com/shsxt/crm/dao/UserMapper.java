package com.shsxt.crm.dao;

import com.shsxt.crm.po.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    public User queryUserByName(String userName);
}