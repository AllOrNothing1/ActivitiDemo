package com.workflow.demo.dao;

import com.workflow.demo.entity.ActHiTaskinst;

public interface ActHiTaskinstMapper {
    int deleteByPrimaryKey(String id);

    int insert(ActHiTaskinst record);

    int insertSelective(ActHiTaskinst record);

    ActHiTaskinst selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ActHiTaskinst record);

    int updateByPrimaryKey(ActHiTaskinst record);
}