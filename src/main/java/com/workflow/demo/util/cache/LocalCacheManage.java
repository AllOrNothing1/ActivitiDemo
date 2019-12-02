package com.workflow.demo.util.cache;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * ConcurrentMapCache改造，增加失效时间的功能
 */
public class LocalCacheManage implements CacheManager {

    private final ConcurrentMap<String, Cache> cacheConcurrentMap = new ConcurrentHashMap<String, Cache>();

    public LocalCacheManage() {
    }

    /**
     *获取缓存方法
     * 当缓存为null时,则创建缓存
     * @param name
     * @return
     */
    @Override
    public Cache getCache(String name) {
        //获取cache
        Cache cache = cacheConcurrentMap.get(name);
        //当cache为null，且dynamic为true时，需要创建新的cache
        if(cache==null){
            synchronized (this.cacheConcurrentMap){
                //再次判断是否为null
                cache = this.cacheConcurrentMap.get(name);
                if (cache == null){
                    //创建cache，并且放入储存cache的map中
                    cache = createConCurrentMapCache(name);
                    this.cacheConcurrentMap.put(name,cache);
                }
            }
        }
        return cache;
    }

    @Override
    public Collection<String> getCacheNames() {
        return Collections.unmodifiableSet(this.cacheConcurrentMap.keySet());
    }

    /**
     * 创建cache对象
     * @param name
     * @return
     */
    private Cache createConCurrentMapCache(String name){
        return new ConcurrentMapCache(name,
                CacheBuilder.newBuilder()
                        .expireAfterWrite(1, TimeUnit.HOURS)
                        .build()
                        .asMap(),
                //是否允许为null值，false不允许
                false);
    }
}
