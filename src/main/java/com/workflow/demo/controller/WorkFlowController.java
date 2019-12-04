package com.workflow.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WorkFlowController {

    public Object createProcessinstance(@RequestBody Map<String,Object> map){
        String key = map.get("key").toString();

        return "";
    }

    @PostMapping(value = "/test")
    public Object test(){
        return "success";
    }
}
