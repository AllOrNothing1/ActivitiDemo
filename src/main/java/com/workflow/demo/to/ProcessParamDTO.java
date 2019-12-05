package com.workflow.demo.to;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

public class ProcessParamDTO implements Serializable {

    private static final long serialVersionUID = 1683668249861683250L;

    @Getter
    @Setter
    private String tenantId;
    @Getter
    @Setter
    private String processInstanceId;
    @Getter
    @Setter
    private Map<String, Object> variables;

}
