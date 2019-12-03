package com.workflow.demo.config;

import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public class ActivitiConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ResourcePatternResolver resourcePatternResolver;

    @Bean
    public StandaloneProcessEngineConfiguration processEngineConfiguration(){
        StandaloneProcessEngineConfiguration configuration = new StandaloneProcessEngineConfiguration();
        configuration.setDataSource(dataSource);
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        configuration.setAsyncExecutorActivate(false);
        return configuration;
    }

    @Bean
    public ProcessEngineFactoryBean processEngine() {
        ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
        processEngineFactoryBean.setProcessEngineConfiguration(processEngineConfiguration());
        return processEngineFactoryBean;
    }

    @Bean
    public RepositoryService repositoryService() throws Exception{
        return processEngine().getObject().getRepositoryService();
    }

    @Bean
    public RuntimeService runtimeService() throws Exception{
        return processEngine().getObject().getRuntimeService();
    }

    @Bean
    public TaskService taskService() throws Exception{
        return processEngine().getObject().getTaskService();
    }

}
