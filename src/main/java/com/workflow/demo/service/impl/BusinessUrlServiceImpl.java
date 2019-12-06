package com.workflow.demo.service.impl;

import com.workflow.demo.dao.BusinessUrlMapper;
import com.workflow.demo.entity.BusinessUrl;
import com.workflow.demo.service.BusinessUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BusinessUrlServiceImpl implements BusinessUrlService {

    @Resource
    private BusinessUrlMapper businessUrlMapper;

    @Override
    public String getBusinessUrl(String tenantId) {
        String productLine = "";
        if (tenantId.contains("@")){
            productLine = tenantId.split("@")[0];
        }
        //BusinessUrl businessUrl = businessUrlMapper.selectByTenantId(productLine, tenantId);
        BusinessUrl businessUrl = businessUrlMapper.selectByTenantId("WFM40", "reg");
        return businessUrl.getUrl();
    }
}
