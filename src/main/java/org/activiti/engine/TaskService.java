package org.activiti.engine;

import java.util.Map;

public interface TaskService {
    void complete(String taskId,String action, Map<String, Object> variables);
}
