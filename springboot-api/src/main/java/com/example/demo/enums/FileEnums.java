package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by fb on 2020/9/17
 */
@Getter
@AllArgsConstructor
public enum  FileEnums {
        FILE_CHECK_PASS(0,"文件格式通过"),
        FILE_IS_EMPTY(1,"文件为空"),
        FILE_SIZE_ERROR(2,"文件大小问题"),
        FILE_TYPE_ERROR(3,"文件类型问题"),
        ;

        private Integer code;
        private String message;
}
