package com.example.demo.vo;

import com.example.demo.util.ExcelColumn;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2019/9/26.
 */
@Getter
@Setter
public class OutEntityVo {
    @ExcelColumn(value = "id", col = 1)
    private Long id;
    @ExcelColumn(value = "department", col = 2)
    private String department;
    @ExcelColumn(value = "price", col = 3)
    private Double price;
    @ExcelColumn(value = "projectName", col = 4)
    private String projectName;

}
