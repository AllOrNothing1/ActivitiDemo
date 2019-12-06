package com.workflow.demo.to;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class FlowParticipants implements Serializable {
    private static final long serialVersionUID = 3150517888519487765L;

    private String tenantId;
    private String processInstanceId;
    private List<Map<String,Object>> participants;
    private List<Map<String,Object>> reminders;
    private String isDirectUpdateUser;

    public String getIsDirectUpdateUser() {
        return isDirectUpdateUser;
    }

    public void setIsDirectUpdateUser(String isDirectUpdateUser) {
        this.isDirectUpdateUser = isDirectUpdateUser;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public List<Map<String, Object>> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Map<String, Object>> participants) {
        this.participants = participants;
    }

    public List<Map<String, Object>> getReminders() {
        return reminders;
    }

    public void setReminders(List<Map<String, Object>> reminders) {
        this.reminders = reminders;
    }
}
