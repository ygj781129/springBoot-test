package com.example.demo.pojo;

import lombok.Data;
import lombok.ToString;

/**
 * Created by fb on 2021/2/1
 */
@Data
@ToString
public class UserInfo {
        private Integer id;
        private String name;
        private String sex;
        private Integer age;
}