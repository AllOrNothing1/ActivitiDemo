package com.workflow.demo.util;

import org.activiti.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Util {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 结束节点触发事件
     * @param execution
     */
    public void endExpression(DelegateExecution execution){
        String url = "";
        String param = "";

        HttpClientUtil.sendHttpPostJson(url,param);
    }

    /**
     * 获取参与人
     * @param execution
     * @param isMultiInstance
     * @param uniqueId
     */
    public void getUsers(DelegateExecution execution,Boolean isMultiInstance,String uniqueId){
        String url = "";
        String param = "";

        String response = HttpClientUtil.sendHttpPostJson(url, param);
        logger.info(response);
    }
}
