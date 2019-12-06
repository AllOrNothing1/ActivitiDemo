package com.workflow.demo.dao;

import com.workflow.demo.entity.ActReExecution;

public interface ActReExecutionMapper {
    int deleteByPrimaryKey(String id);

    int insert(ActReExecution record);

    int insertSelective(ActReExecution record);

    ActReExecution selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ActReExecution record);

    int updateByPrimaryKey(ActReExecution record);
}