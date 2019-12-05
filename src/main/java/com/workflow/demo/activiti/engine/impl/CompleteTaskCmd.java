package com.workflow.demo.activiti.engine.impl;

import org.activiti.engine.impl.cmd.NeedsActiveTaskCmd;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Map;

public class CompleteTaskCmd extends NeedsActiveTaskCmd<Void> implements Serializable {
    private static final long serialVersionUID = -936041864719366479L;

    private String action;
    private Map<String,Object> variables;
    private boolean localScope;

    public CompleteTaskCmd(String taskId,Map<String,Object> variables) {
        super(taskId);
        this.variables = variables;
    }

    public CompleteTaskCmd(String taskId,Map<String,Object> variables,String action) {
        super(taskId);
        this.variables = variables;
        this.action = action;
    }

    public CompleteTaskCmd(String taskId,Map<String,Object> variables,boolean localScope) {
        super(taskId);
        this.variables = variables;
        this.localScope = localScope;
    }

    @Override
    protected Void execute(CommandContext commandContext, TaskEntity taskEntity) {
        if (variables != null){
            if (localScope){
                taskEntity.setVariablesLocal(variables);
            } else if (taskEntity.getExecutionId() != null){
                taskEntity.setExecutionVariables(variables);
            } else {
                taskEntity.setVariables(variables);
            }
        }

        if (StringUtils.isNotBlank()){
            taskEntity.complete(variables,localScope);
        } else {
            taskEntity.complete(action,variables,localScope);
        }
        return null;
    }
}
