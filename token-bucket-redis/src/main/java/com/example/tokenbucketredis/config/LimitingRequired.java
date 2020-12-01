package com.example.tokenbucketredis.config;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LimitingRequired {
        boolean required() default true;
}