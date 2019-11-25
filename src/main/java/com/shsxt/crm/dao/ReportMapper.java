package com.shsxt.crm.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReportMapper {

    public List<Map> queryCustomerLevel();
}