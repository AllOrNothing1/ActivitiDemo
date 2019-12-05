package com.workflow.demo.service;

import com.workflow.demo.to.ProcessParamDTO;
import java.util.Map;

public interface ActivitiService {

    /**
     * 部署流程模型
     * @param key
     * @param tenantId
     * @param modelId
     * @return
     * @throws Exception
     */
    Map<String,String> deployModel(String key, String tenantId, String modelId) throws Exception;

    /**
     * 检查流程模型是否部署
     * @param key
     * @param tenantId
     * @return
     */
    boolean isDeployModel(String key,String tenantId) throws Exception;

    /**
     * 创建流程id
     * @param key
     * @param tenantId
     * @return
     */
    Map<String,Object> createProcessinstance(String key,String tenantId) throws Exception;

    /**
     * 申请表单
     * @param processParamDTO
     * @return
     * @throws Exception
     */
    Object apply(ProcessParamDTO processParamDTO) throws Exception;

    /**
     * 审批表单
     * @param processParamDTO
     * @return
     * @throws Exception
     */
    Object approve(ProcessParamDTO processParamDTO) throws Exception;

    /**
     * 驳回表单
     * @param processParamDTO
     * @return
     * @throws Exception
     */
    Object reject(ProcessParamDTO processParamDTO) throws Exception;

    /**
     * 拒绝表单
     * @param processParamDTO
     * @return
     * @throws Exception
     */
    Object refuse(ProcessParamDTO processParamDTO) throws Exception;

    /**
     * 撤回表单
     * @param processParamDTO
     * @return
     * @throws Exception
     */
    Object withdraw(ProcessParamDTO processParamDTO) throws Exception;

    /**
     * 强制通过表单到结束
     * @param processParamDTO
     * @return
     * @throws Exception
     */
    Object forceComplete(ProcessParamDTO processParamDTO) throws Exception;

    Object addSign();
    Object decreaseSign();
}
