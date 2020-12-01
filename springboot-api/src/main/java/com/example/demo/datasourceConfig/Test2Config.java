package com.example.demo.datasourceConfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by fb on 2020/8/6
 */
@Data
@ConfigurationProperties(prefix = "spring.datasource.test2")
public class Test2Config {
        private String url;
        private String username;
        private String password;
        private int minPoolSize;
        private int maxPoolSize;
        private int maxLifetime;
        private int borrowConnectionTimeout;
        private int loginTimeout;
        private int maintenanceInterval;
        private int maxIdleTime;
        private String testQuery;
        private String uniqueResourceName;
}
