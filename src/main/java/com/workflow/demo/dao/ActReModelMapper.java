package com.workflow.demo.dao;

import com.workflow.demo.entity.ActReModel;

public interface ActReModelMapper {
    int deleteByPrimaryKey(String id);

    int insert(ActReModel record);

    int insertSelective(ActReModel record);

    ActReModel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ActReModel record);

    int updateByPrimaryKey(ActReModel record);
}