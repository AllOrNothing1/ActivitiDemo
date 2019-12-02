package com.workflow.demo.util.cache;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CacheConfig {
    @Bean
    @Primary
    public CacheManager cacheManager(){
        return new LocalCacheManage() ;
    }
}
