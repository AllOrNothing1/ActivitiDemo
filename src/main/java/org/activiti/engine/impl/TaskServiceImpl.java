package org.activiti.engine.impl;

import org.activiti.engine.TaskService;
import org.activiti.engine.impl.cmd.CompleteTaskCmd;

import java.util.*;

public class TaskServiceImpl extends ServiceImpl implements TaskService {

    @Override
    public void complete(String taskId, String status, Map<String, Object> variables) {
        commandExecutor.execute(new CompleteTaskCmd(taskId,status,variables));
    }
}
