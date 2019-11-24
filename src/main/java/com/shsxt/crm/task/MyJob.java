package com.shsxt.crm.task;

import com.shsxt.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class MyJob {

    @Autowired
    private CustomerService customerService;

    @Scheduled(cron = "0/5 * * * * ? ")
    public void job01(){
        System.out.println("=====job01-->>start=====");
        customerService.addLossCustomer();
        System.out.println("=====job01-->>end=====");
    }
}
