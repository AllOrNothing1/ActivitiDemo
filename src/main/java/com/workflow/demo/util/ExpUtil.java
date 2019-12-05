package com.workflow.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExpUtil {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public Object adjustExp(Exception e) {
        if (e.getMessage() != null){
            return "";
        } else {
            return JsonUtil.Fail(1,"error");
        }
    }
}
