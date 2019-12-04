package com.workflow.demo;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class WorkflowDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkflowDemoApplication.class, args);
    }

}
