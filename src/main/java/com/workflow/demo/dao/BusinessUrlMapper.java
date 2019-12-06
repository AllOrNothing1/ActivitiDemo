package com.workflow.demo.dao;

import com.workflow.demo.entity.BusinessUrl;

public interface BusinessUrlMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BusinessUrl record);

    int insertSelective(BusinessUrl record);

    BusinessUrl selectByTenantId(String productLine,String tenantId);

    int updateByPrimaryKeySelective(BusinessUrl record);

    int updateByPrimaryKey(BusinessUrl record);
}