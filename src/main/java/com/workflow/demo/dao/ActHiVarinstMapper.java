package com.workflow.demo.dao;

import com.workflow.demo.entity.ActHiVarinst;

public interface ActHiVarinstMapper {
    int deleteByPrimaryKey(String id);

    int insert(ActHiVarinst record);

    int insertSelective(ActHiVarinst record);

    ActHiVarinst selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ActHiVarinst record);

    int updateByPrimaryKey(ActHiVarinst record);
}