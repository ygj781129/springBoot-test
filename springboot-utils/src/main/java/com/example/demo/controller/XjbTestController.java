package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2019/9/29.
 */
@RestController
@RequestMapping("/xjb")
public class XjbTestController {


    @GetMapping("/findXJBnam22e")
    public String findXJ55Bname(){
        String ff="jfkldjfk/jk.jfl,kdj|gagkd/*-aghkksdjgl|76u*tyrj/j";
        List<String> list = Arrays.asList(ff.split("\\|"));
        return ff;
    }




}
