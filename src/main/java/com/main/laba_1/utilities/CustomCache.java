package com.main.laba_1.utilities;


public interface CustomCache {
    void addToCache(String key, Object value);

    void removeFromCache(String key);

    void updateCache(String key, Object value);

    Object getFromCache(String key);

    boolean containsKey(String key);
}
