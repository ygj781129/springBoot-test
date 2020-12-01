package com.example.tokenbucket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
public class TokenBucketApplication {

        public static void main(String[] args) {
                SpringApplication.run(TokenBucketApplication.class, args);
        }

}
