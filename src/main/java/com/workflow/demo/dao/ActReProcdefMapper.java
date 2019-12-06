package com.workflow.demo.dao;

import com.workflow.demo.entity.ActReProcdef;

public interface ActReProcdefMapper {
    int deleteByPrimaryKey(String id);

    int insert(ActReProcdef record);

    int insertSelective(ActReProcdef record);

    ActReProcdef selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ActReProcdef record);

    int updateByPrimaryKey(ActReProcdef record);
}