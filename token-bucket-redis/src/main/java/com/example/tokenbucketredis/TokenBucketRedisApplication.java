package com.example.tokenbucketredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
public class TokenBucketRedisApplication {

        public static void main(String[] args) {
                SpringApplication.run(TokenBucketRedisApplication.class, args);
        }

}
