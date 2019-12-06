package com.workflow.demo.dao;

import com.workflow.demo.entity.BusinessUrl;

public interface BusinessUrlMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BusinessUrl record);

    int insertSelective(BusinessUrl record);

    BusinessUrl selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusinessUrl record);

    int updateByPrimaryKey(BusinessUrl record);
}