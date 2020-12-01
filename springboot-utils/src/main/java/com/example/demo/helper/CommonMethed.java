package com.example.demo.helper;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by Administrator on 2019/8/30.
 */

public class CommonMethed {


    public static Date getCurDate() {
        Date d = new Date();
        return d;
    }
}
