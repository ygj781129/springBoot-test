package com.example.demo.cacheConfig;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.hamcrest.beans.PropertyUtil;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Created by fb on 2021/2/1
 */
@EnableCaching
@Configuration
public class CaffeineCacheConfig {
        /**
         * 配置缓存管理器
         * @return
         */
        @Bean("caffeineCacheManager")
        public CacheManager cacheManager() {
                CaffeineCacheManager cacheManager = new CaffeineCacheManager();
                cacheManager.setCaffeine(Caffeine.newBuilder()
                        // 设置最后一次写入或访问后经过固定时间过期
                        .expireAfterAccess(60, TimeUnit.SECONDS)
                        // 初始的缓存空间大小
                        .initialCapacity(100)
                        // 缓存的最大条数
                        .maximumSize(1000));
                return cacheManager;
        }

}

