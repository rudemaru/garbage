package com.main.laba_1.utilities.impl;

import com.main.laba_1.utilities.CustomCache;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import java.util.Map;

@Component
public class CustomCacheImpl implements CustomCache {
    Logger logger = Logger.getLogger(getClass().getName());
    private final Map<String, Object> cacheMap = new ConcurrentHashMap<>();
    private final List<String> cachePriorityList = new ArrayList<>();

    @Override
    public void addToCache(String key, Object value) {
        cacheMap.put(key, value);
        if(cacheMap.keySet().size() > 3){
            String log = String.format("CACHE OVERFLOW: DELETING\tKey: %s\t Value: %s %n", cachePriorityList.get(0), cacheMap.get(cachePriorityList.get(0)));
            logger.info(log);
            removeFromCache(cachePriorityList.get(0));
            cachePriorityList.remove(0);
        }
        cachePriorityList.add(key);
        String log = String.format("ADDED:\tKey: %s\t Value: %s %n", key, cacheMap.get(key));
        logger.info(log);
        log = String.format("CACHE PRIORITY LIST: %s %n", cachePriorityList);
        logger.info(log);
    }

    @Override
    public void removeFromCache(String key) {
        cacheMap.remove(key);
    }

    @Override
    public void updateCache(String key, Object value) {
        String log = String.format("BEFORE:\tKey: %s\tValue: %s %n", key, cacheMap.get(key));
        logger.info(log);
        cachePriorityList.remove(key);
        addToCache(key, value);
        log = String.format("AFTER:\tKey: %s\tValue: %s %n", key, cacheMap.get(key));
        logger.info(log);
    }

    @Override
    public Object getFromCache(String key) {
        if(cachePriorityList.contains(key)){
            cachePriorityList.remove(key);
            cachePriorityList.add(key);
        }
        return cacheMap.get(key);
    }

    @Override
    public boolean containsKey(String key) {
        return cacheMap.containsKey(key);
    }
}
