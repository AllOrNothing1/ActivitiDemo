package com.workflow.demo.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.workflow.demo.service.ActivitiService;
import com.workflow.demo.to.ProcessParamDTO;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ActivitiServiceImpl implements ActivitiService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    /**
     * 部署流程模型
     * @param key
     * @param tenantId
     * @param modelId
     * @return
     * @throws Exception
     */
    @Override
    public Map<String,String> deployModel(String key,String tenantId,String modelId) throws Exception{
        Map<String,String> resultMap = new HashMap<>();

        Model modelData = repositoryService.getModel(modelId);
        ObjectNode objectNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
        byte[] bytes = null;
        JsonNode jsonNode = new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelId));

        BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
        BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(jsonNode);
        bytes = new BpmnXMLConverter().convertToXML(bpmnModel);
        String processName = modelData.getName() + ".bpmn20.xml";

        Deployment deployment = repositoryService.createDeployment().name(modelData.getName())
                .addString(processName, new String(bytes, "UTF-8")).tenantId(modelData.getTenantId()).deploy();
        resultMap.put("deploymentId",deployment.getId());

        return resultMap;

    }

    /**
     * 检查流程模型是否部署
     * @param key
     * @param tenantId
     * @return
     */
    @Override
    public boolean isDeployModel(String key, String tenantId) {
        long count = repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(tenantId).processDefinitionKey(key).count();
        if (count == 0){
            return false;
        }
        return true;
    }

    /**
     * 创建流程id
     * @param key
     * @param tenantId
     * @return
     */
    @Override
    public Map<String,Object> createProcessinstance(String key, String tenantId) {
        //ProcessInstance processInstance = runtimeService.startProcessInstanceByKeyAndTenantId(key,key,tenantId);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key,tenantId);

        Map<String,Object> map = new HashMap<>();
        map.put("processInstanceId",processInstance.getProcessInstanceId());
        map.put("tenantId",tenantId);
        map.put("key",processInstance.getProcessDefinitionKey());
        map.put("BusinessKey","");
        return map;
    }

    /**
     * 申请表单
     * @param processParamDTO
     * @return
     * @throws Exception
     */
    @Override
    public Object apply(ProcessParamDTO processParamDTO) throws Exception {
        taskService.complete(processParamDTO.getProcessInstanceId());
        return null;
    }

    /**
     * 审批表单
     * @param processParamDTO
     * @return
     * @throws Exception
     */
    @Override
    public Object approve(ProcessParamDTO processParamDTO) throws Exception {
        taskService.complete(processParamDTO.getProcessInstanceId());
        return null;
    }

    /**
     * 驳回表单
     * @param processParamDTO
     * @return
     * @throws Exception
     */
    @Override
    public Object reject(ProcessParamDTO processParamDTO) throws Exception {
        return null;
    }

    /**
     * 拒绝表单
     * @param processParamDTO
     * @return
     * @throws Exception
     */
    @Override
    public Object refuse(ProcessParamDTO processParamDTO) throws Exception {
        return null;
    }

    /**
     * 撤回表单
     * @param processParamDTO
     * @return
     * @throws Exception
     */
    @Override
    public Object withdraw(ProcessParamDTO processParamDTO) throws Exception {
        return null;
    }

    /**
     * 强制通过表单到结束
     * @param processParamDTO
     * @return
     * @throws Exception
     */
    @Override
    public Object forceComplete(ProcessParamDTO processParamDTO) throws Exception {
        return null;
    }

    @Override
    public Object addSign() {
        return null;
    }

    @Override
    public Object decreaseSign() {
        return null;
    }


}
