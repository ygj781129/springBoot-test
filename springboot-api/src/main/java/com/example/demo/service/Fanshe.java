package com.example.demo.service;

import com.example.demo.pojo.Demo;
import com.example.demo.pojo.Student;


import java.lang.reflect.*;
import java.security.MessageDigest;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/11/5.
 */
public class Fanshe {

    public static void main333(String[] args) throws Exception{
        //第一种方式获取Class对象
        Student stu1 = new Student();//这一new 产生一个Student对象，一个Class对象。
        Class stuClass = stu1.getClass();//获取Class对象
        System.out.println(stuClass.getName());

        Class stuClass2 = Student.class;
        System.out.println(stuClass == stuClass2);//判断第一种方式获取的Class对象和第二种方式获取的是否是同一个


        Class stuClass3 = Class.forName("com.example.demo.pojo.Student");//注意此字符串必须是真实路径，就是带包名的类路径，包名.类名
        System.out.println(stuClass3 == stuClass2);//判断三种方式是否获取的是同一个Class对象

    }

    public static void main77(String[] args) throws Exception{
//1.加载Class对象
        Class clazz = Class.forName("com.example.demo.pojo.Student");
        //Student clazz = new Student();

        //2.获取所有公有构造方法
        System.out.println("**********************所有公有构造方法*********************************");
        Constructor[] conArray = clazz.getConstructors();
        for(Constructor c : conArray){
            System.out.println(c);
        }


        System.out.println("************所有的构造方法(包括：私有、受保护、默认、公有)***************");
        conArray = clazz.getDeclaredConstructors();
        for(Constructor c : conArray){
            System.out.println(c);
        }

        System.out.println("*****************获取公有、无参的构造方法*******************************");
        Constructor con = clazz.getConstructor(null);
        //1>、因为是无参的构造方法所以类型是一个null,不写也可以：这里需要的是一个参数的类型，切记是类型
        //2>、返回的是描述这个无参构造函数的类对象。

        System.out.println("con = " + con);
        //调用构造方法
        Object obj = con.newInstance();
        //	System.out.println("obj = " + obj);
        //	Student stu = (Student)obj;

        System.out.println("******************获取私有构造方法，并调用*******************************");
        con = clazz.getDeclaredConstructor(char.class);
        System.out.println(con);
        //调用构造方法
        con.setAccessible(true);//暴力访问(忽略掉访问修饰符)
        obj = con.newInstance('男');
    }

    public static void mainoo(String[] args)throws Exception {

        Method m = Demo.class.getMethod("test01", Map.class,List.class);
        Type[] t = m.getGenericParameterTypes();

        for (Type paramType : t) {
            System.out.println("#"+paramType);
            if(paramType instanceof ParameterizedType){
                //获取泛型中的具体信息
                Type[] genericTypes = ((ParameterizedType) paramType).getActualTypeArguments();
                for (Type genericType : genericTypes) {
                    System.out.println("泛型类型："+genericType);
                }
            }
        }
        System.out.println("*******************************************************************");
///////////*************************************************************
        //获得指定方法返回值泛型信息
        Method m2 = Demo.class.getMethod("test02", null);
        Type returnType = m2.getGenericReturnType();
        if(returnType instanceof ParameterizedType){
            Type[] genericTypes = ((ParameterizedType) returnType).getActualTypeArguments();

            for (Type genericType : genericTypes) {
                System.out.println("返回值，泛型类型："+genericType);
            }
        }
    }

    public static void mai(String[] args) {
        Student student = null;
        try {
            Class clazz = Class.forName("com.example.demo.pojo.Student");
            student=(Student)clazz.newInstance();
            System.out.println(clazz);
            student = (Student)Class.forName("com.example.demo.pojo.Student").newInstance();
            System.out.println(student);
            //Method method = student.getClass().getMethod("say", String.class, Integer.class);
            Method method=clazz.getMethod("desc");
            // 通过Method对象，调用对象中的方法。
            method.invoke(student);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main7(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("GP","billet.t_billet_baseprice");//钢坯
        map.put("MJ","coke.t_coke_baseprice");//煤焦
        map.put("IG","iron_ore.t_ironore_domestic_baseprice");//国产矿
        map.put("IJ","iron_ore.t_ironore_imported_baseprice");//进口矿
        map.put("FG","scrap.t_scrap_baseprice");//废钢
        map.put("LW","screw_steel.t_screw_baseprice");//螺纹钢
        map.put("XG","section.t_section_baseprice");//型钢
        map.put("XC","wire_rod.t_wirerod_baseprice");//线材
        map.put("DG","strip_steel.t_strip_baseprice");//带钢
        map.put("GC","pipe_iron.t_pipe_baseprice");//管材
        map.put("BC","plate.t_plate_baseprice");//板材
        List<String> list=new ArrayList<>();
        for(Map.Entry<String,String> vo:map.entrySet()){
            list.add(vo.getKey());
        }
       list.forEach(System.out::println);
    }


    public static void mainfff(String[] args) {
        String s="888888";
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
          System.out.println(str);
        } catch (Exception e) {
        e.printStackTrace();
        }
    }

    public static void testList2(List list)throws Exception {

        for (Object object : list) {
            //System.out.println(object.toString());
            System.out.println(object instanceof Student);
            Class stuClass = object.getClass();//获取Class对象
            System.out.println(stuClass.getName());
            Field fromDeclaredField=stuClass.getDeclaredField("name");
            fromDeclaredField.setAccessible(true);
            String string=fromDeclaredField.get(object).toString();


            System.out.println("fdfdgdg---"+string);
            // begin--------------------->         这里是我测试一下反射的使用的，不用请忽略
            //通过反射获得object对象对应的实体类的属性、及属性值
//            try {
//                //获得object对象对应的所有已申明的属性，包括public、private、和protected
//                Field[] fields = object.getClass().getDeclaredFields();
//
//                for (Field field : fields) {
//
//                    //不加这一句 private的属性无法访问的呀
//                    field.setAccessible(true);
//                    //获得属性名称
//                    System.out.println("获得属性名：" + field.getName());
//                    //获得属性值
//                    System.out.println("获得属性值：" + field.get(field.getName()));
//
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }

    }

    public static void maineeee(String[] args) throws Exception {
//
//        Student student = new Student();
//        student.setName("454545");
//        Class stuClass = new Student().getClass();//获取Class对象
//        Field fromDeclaredField = stuClass.getDeclaredField("name");
//        fromDeclaredField.setAccessible(true);
//        Object obj = fromDeclaredField.get(student);
//        Double dataDouble=obj==null?null:Double.parseDouble(obj.toString());

       // System.out.println(new LocalDate("2018-01-07").plusDays(1139).toString());

    }


    public static void main(String[] a){

        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        //获取当前时间
        LocalDateTime currentDate =  LocalDateTime.of(2020, 12, 30, 0, 0, 0);

        //获取年份
        int year = currentDate.getYear();
        System.out.println("获取当前年份:" + year);
        //获取月份
        int month = currentDate.getMonthValue();
        System.out.println("获取当前月份:" + month);
        //获取当前周
        int week = currentDate.getDayOfWeek().getValue();
        System.out.println("获取当前周:" + week);
        //获取当前时间第X周
        /*
        public static WeekFields of​(DayOfWeek firstDayOfWeek, int minimalDaysInFirstWeek)
        从第一天和最小日期获得WeekFields的实例。
        第一天的每周定义ISO DayOfWeek ，即一周中的第一天。 第一周的最小天数定义一个月或一年中必须存在的天数，从第一天开始，在将一周计算为第一周之前。 值1将计算作为第一周的一部分的月或年的第一天，而值7将要求整个七天在新的月或年中。

        WeekFields实例是单例; 对于firstDayOfWeek和minimalDaysInFirstWeek的每个唯一组合，将返回相同的实例。

        参数
        firstDayOfWeek - 一周的第一天，不是null
        minimalDaysInFirstWeek - 第一周的最小天数，从1到7
         */
//        WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY,1);
//        int weeks = currentDate.get(weekFields.weekOfYear());
//        System.out.println("获取当前时间第" + weeks + "周");

    }

}
