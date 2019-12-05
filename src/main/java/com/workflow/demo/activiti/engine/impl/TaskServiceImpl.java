package com.workflow.demo.activiti.engine.impl;

import com.workflow.demo.activiti.engine.TaskService;
import org.activiti.engine.impl.ServiceImpl;
import org.activiti.engine.impl.cmd.CompleteTaskCmd;
import org.activiti.engine.impl.persistence.entity.VariableInstance;
import org.activiti.engine.task.*;

import java.io.InputStream;
import java.util.*;

public class TaskServiceImpl extends ServiceImpl implements TaskService {

    @Override
    public void complete(String taskId, String status, Map<String, Object> variables) {
        commandExecutor.execute(new CompleteTaskCmd(taskId,status,variables));
    }
}
