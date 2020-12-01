package com.example.javaguava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
public class JavaGuavaApplication {

        public static void main(String[] args) {
                SpringApplication.run(JavaGuavaApplication.class, args);
        }

}
