package com.example.demo.util;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2019/9/26.
 * 自定义实体类所需要的bean
 * ExcelColumn
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelColumn {

    String value() default "";

    int col() default 0;
}