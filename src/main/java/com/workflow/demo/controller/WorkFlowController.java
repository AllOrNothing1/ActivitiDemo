package com.workflow.demo.controller;

import com.workflow.demo.service.ActivitiService;
import com.workflow.demo.to.ProcessParamDTO;
import com.workflow.demo.util.ExpUtil;
import com.workflow.demo.util.JsonUtil;
import jdk.nashorn.internal.ir.ObjectNode;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

@RestController("/workflow")
public class WorkFlowController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ActivitiService activitiService;

    @Autowired
    private ExpUtil expUtil;

    @Autowired
    private RepositoryService repositoryService;


    /**
     * 部署流程模型
     * @param map
     * @return
     * @throws Exception
     */
    @PostMapping(value = "deployModel")
    public Object deployModel(@RequestBody Map<String,Object> map) throws Exception {
        try {
            String key = map.get("key").toString();
            String tenantId = map.get("tenantId").toString();
            String modelId = map.get("key").toString();
            Map<String, String> map1 = activitiService.deployModel(key, tenantId, modelId);
            return JsonUtil.Success(map1);
        } catch (Exception e) {
            return expUtil.adjustExp(e);
        }
    }

    /**
     * 创建流程id
     * @param map
     * @return
     * @throws Exception
     */
    @PostMapping(value = "createProcessinstance")
    public Object createProcessinstance(@RequestBody Map<String,Object> map) throws Exception{
        String key = map.get("key").toString();
        String tenantId = map.get("tenantId").toString();
        boolean deployModel = activitiService.isDeployModel(key, tenantId);
        if (!deployModel){
            throw new Exception("流程模型未部署");
        }
        Map<String,Object> map1 = activitiService.createProcessinstance(key, tenantId);
        return JsonUtil.Success(map1);
    }

    @PostMapping(value = "apply")
    public Object apply(@RequestBody ProcessParamDTO processParamDTO){
        try {
            activitiService.apply(processParamDTO);
            return JsonUtil.Success("");
        } catch (Exception e) {
            return expUtil.adjustExp(e);
        }
    }

    @PostMapping(value = "approve")
    public Object approve(@RequestBody ProcessParamDTO processParamDTO){
        try {
            activitiService.approve(processParamDTO);
            return JsonUtil.Success("");
        } catch (Exception e) {
            return expUtil.adjustExp(e);
        }
    }

    /**
     * 测试接口是否能调通
     * @return
     */
    @PostMapping(value = "/test")
    public Object test(){
        return "success";
    }

    @PostMapping("deploy")
    public void deployementProcessDefinitionByInputStream() throws FileNotFoundException {
        //获取资源相对路径
        String bpmnPath = "D:\\MyProject\\Workflow\\workflowDemo\\src\\main\\resources\\MyProcess.bpmn";
        String pngPath = "D:\\MyProject\\Workflow\\workflowDemo\\src\\main\\resources\\MyProcess.png";

        //读取资源作为一个输入流
        FileInputStream bpmnfileInputStream = new FileInputStream(bpmnPath);
        FileInputStream pngfileInputStream = new FileInputStream(pngPath);

        Deployment deployment = repositoryService//获取流程定义和部署对象相关的Service
                .createDeployment()//创建部署对象
                .addInputStream("MyProcess.bpmn",bpmnfileInputStream)
                .addInputStream("MyProcess.png", pngfileInputStream)
                .deploy();//完成部署
        System.out.println("部署ID："+deployment.getId());//1
        System.out.println("部署时间："+deployment.getDeploymentTime());
    }

}
