package com.shsxt.crm.service;

import com.shsxt.crm.dao.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private ReportMapper reportMapper;

    public List<Map> queryCustomerLevel(){
        return reportMapper.queryCustomerLevel();
    }
}
