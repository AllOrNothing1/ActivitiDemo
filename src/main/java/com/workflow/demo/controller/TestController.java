package com.workflow.demo.controller;

import com.workflow.demo.service.BusinessUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private BusinessUrlService businessUrlService;

    /**
     * 测试接口是否能调通
     * @return
     */
    @PostMapping(value = "/test")
    public Object test(){
        String url = businessUrlService.getBusinessUrl("reg");
        return url;
    }

}
