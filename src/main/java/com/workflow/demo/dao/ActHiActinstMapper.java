package com.workflow.demo.dao;

import com.workflow.demo.entity.ActHiActinst;

public interface ActHiActinstMapper {
    int deleteByPrimaryKey(String id);

    int insert(ActHiActinst record);

    int insertSelective(ActHiActinst record);

    ActHiActinst selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ActHiActinst record);

    int updateByPrimaryKey(ActHiActinst record);
}