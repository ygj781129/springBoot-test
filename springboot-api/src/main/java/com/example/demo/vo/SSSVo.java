package com.example.demo.vo;

import com.example.demo.pojo.Student;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by fb on 2020/11/18
 */
@Getter
@Setter
@ApiModel(description= "返回类型")
public class SSSVo {
        @ApiModelProperty(value = "学生信息")
        private Student student;
        @ApiModelProperty(value = "标签")
        private String tag;
}
