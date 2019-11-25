package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("report")
public class ReportController extends BaseController {

    @Autowired
    private ReportService reportService;

    @RequestMapping("/{id}")
    public String index(@PathVariable Integer id){
        if(id==1){
            return "report_customer_level";
        }
        return "error";
    }

    @RequestMapping("queryCustomerLevel")
    @ResponseBody
    public List<Map> queryCustomerLevel(){
        return reportService.queryCustomerLevel();
    }
}
