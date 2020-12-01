package com.example.limitierredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
public class LimitierRedisApplication {

        public static void main(String[] args) {
                SpringApplication.run(LimitierRedisApplication.class, args);
        }

}
