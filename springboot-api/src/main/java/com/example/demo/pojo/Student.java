package com.example.demo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Administrator on 2019/11/5.
 */
//建造者模式
@Builder
//无参构造方法
@NoArgsConstructor
//全参构造方法
@AllArgsConstructor
//toString方法
@ToString
@Getter
@Setter
@ApiModel(description= "学生实体类")
public class Student implements Serializable {
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -5809782578272943999L;
    @ApiModelProperty(value = "姓名",notes = "fdfdfdfdfdfdfd")
    private  String  name;
    @ApiModelProperty(value = "年龄")
    private  int age;
    @ApiModelProperty(value = "性别")
    private String sex;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(getName(), student.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
//    //---------------构造方法-------------------
//    //（默认的构造方法）
//    Student(String str){
//        System.out.println("(默认)的构造方法 s = " + str);
//    }
//
//    //无参构造方法
//    public Student(){
//        System.out.println("调用了公有、无参构造方法执行了。。。");
//    }
//
//    //有一个参数的构造方法
//    public Student(char name){
//        System.out.println("姓名：" + name);
//    }
//
    //有多个参数的构造方法
    public Student(String name ,Integer age){
        System.out.println("姓名："+name+"年龄："+ age);//这的执行效率有问题，以后解决。
    }
//
//    //受保护的构造方法
//    protected Student(boolean n){
//        System.out.println("受保护的构造方法 n = " + n);
//    }
//
//    //私有构造方法
//    private Student(int age){
//        System.out.println("私有的构造方法   年龄："+ age);
//    }
//
//
//    // 定义普通方法
//    public void say() {
//        System.out.println("say方发：我叫" + name + "," + age + "岁了！");
//    }
//
//    // 定义重载方法
//    public void say(String name, Integer age) {
//        System.out.println("重载say方法：我叫" + name + "," + age + "岁了！");
//    }
//
//    // 私有方法
//    public void desc() {
//        System.out.println("我超甜！");
//    }


}
