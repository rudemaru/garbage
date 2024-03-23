package com.main.laba_1.configurations;

import com.main.laba_1.utilities.impl.CustomCacheImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {
    @Bean
    public CustomCacheImpl customCache(){
        return new CustomCacheImpl();
    }
}
