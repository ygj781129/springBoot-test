package com.example.demo.pojo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/11/5.
 */
public class Demo {

    //定义两个带泛型的方法
    public void test01(Map<String,Student> map, List<Student> list){
        System.out.println("Demo.test01()");
    }
    public Map<Integer,Student> test02(){
        System.out.println("Demo.test02()");
        return null;
    }
}
