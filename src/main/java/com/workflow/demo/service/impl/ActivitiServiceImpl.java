package com.workflow.demo.service.impl;

import com.workflow.demo.service.ActivitiService;
import org.activiti.engine.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ActivitiServiceImpl implements ActivitiService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RepositoryService repositoryService;


    /**
     * 检查流程模型是否部署
     * @param key
     * @param tenantId
     * @return
     */
    @Override
    public boolean isDeployModel(String key, String tenantId) {
        long count = repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(tenantId).processDefinitionKey(key).count();
        if (count == 0){
            return false;
        }
        return true;
    }
}
