package com.shsxt.crm.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("saleChance")
public class SaleChanceController extends Serializers.Base {

    @RequestMapping("index")
    public String index(){
        return "sale_chance";
    }
}
