package com.example.demo.service;

import com.example.demo.SpringbootApiApplication;
import com.example.demo.mapper.test01.TNavigationMapper;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.TNavigation;
import com.example.demo.pojo.TNavigationExample;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by fb on 2020/5/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApiApplication.class)
public class Interview {



        public static void main0000(String[] args) {
                // 使用双冒号::来构造静态函数引用
                Function<String, Integer> fun = Integer::parseInt;
                Integer value = fun.apply("123");
                System.out.println(value);
                // 使用双冒号::来构造非静态函数引用
                String content = "Hello JDK8";
                Function<Integer, String> func = content::substring;
                String result = func.apply(1);
                System.out.println(result);

                // 构造函数引用
                BiFunction<String, Integer, Student> biFunction = Student::new;
                Student user = biFunction.apply("mengday", 28);
                System.out.println(user.toString());

                // 函数引用也是一种函数式接口，所以也可以将函数引用作为方法的参数
                sayHello(String::toUpperCase, "hello");


        }

        public static void main(String[] args) {
//orElse 有，就用自身值。  为null，就用orElse后面的值。
//有正品用正品，没正品用替代品
                //System.out.println(Optional.ofNullable(null).orElse("ffdfd"));  // orElse
//                System.out.println(Optional.ofNullable(null).orElse("替代品"));
//
//
                // orElseGet 它可以传入一个supplier接口，里面可以花样实现逻辑
                System.out.println(Optional.ofNullable("宝马").orElseGet(()-> B()));  // 有宝马就不用走路
                //System.out.println(Optional.ofNullable(null).orElseGet(()->B()));  // 没宝马，可以骑自行车
                //System.out.println(Optional.ofNullable(null).orElseGet(()->"电动车"));  // 没宝马，也可以骑电动车
        }


        static String B() {
                System.out.println("B()...");
                return "B";
        }
        // 方法有两个参数，一个是
         static void sayHello(Function<String, String> func, String parameter){
                String result = func.apply(parameter);
                System.out.println(result);
        }
//
//        public static void main(String[] args) {
//               Student student1,student2;
//               student1=new Student();
//               student2=new Student();
//                student2=student1;
//                student2.setName("2");
//                student1.setName("4");
//                System.out.println(student1.equals(student2));
//                System.out.println(student1==student2);
//                System.out.println(student1.getName());
//                System.out.println(student2.getName());
//
//
//                Double indexData = (0/0.4)*100;
//
//                System.out.println(indexData);
//        }


        @Test
        public void fff(){

//                String str1 = "通话";
//                String str2 = "重地";
//                System.out.println(String.format("str1：%d | str2：%d",  str1.hashCode(),str2.hashCode()));
//                System.out.println(str1.hashCode()==str2.hashCode());

                Student s1=Student.builder().name("qq").build();
                Student s2=Student.builder().name("qq").build();
                System.out.println(s1.hashCode()==s2.hashCode());
                System.out.println(s1.equals(s2));

        }


        @Test
        public void round(){
//                double data = 3.02;
//                //利用字符串格式化的方式实现四舍五入,保留1位小数
//                String result = String.format("%.3f",data);
//                //1代表小数点后面的位数, 不足补0。f代表数据是浮点类型。保留2位小数就是“%.2f”，依此累推。
//                System.out.println(result);//输出3.0


//                double data = -1.5;
//                //利用BigDecimal来实现四舍五入.保留一位小数
//                double result = new BigDecimal(data).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
//                //1代表保留1位小数,保留两位小数就是2,依此累推
//                //BigDecimal.ROUND_HALF_UP 代表使用四舍五入的方式
//                System.out.println(result);//输出3.0
//
//                System.out.println( Math.round(-1.5));
                //String suffix = orginalFileName.substring(orginalFileName.lastIndexOf(".") + 1);
                String name="abchgf.jpg";
                if (!StringUtils.isEmpty(name)) {
                        System.out.println(this.getExt(name));
                }
                System.out.println(this.getExt("ffff"));
        }

        private String getExt(String name){
                if(name == null || "".equals(name) || !name.contains("."))
                        return "";
                return name.substring(name.lastIndexOf(".")+1);
        }

        @Test
        public void f(){
                Map<String, List<String>> testSessionMap=new HashMap<String, List<String>>();

                List<String> list=testSessionMap.get("w");
                if(list==null){
                        list=Lists.newArrayList();
                }
                list.add("1");
                testSessionMap.put("w",list);

        }
        @Resource
        private TNavigationMapper navigationMapper;
        @Autowired
        private TryTest tryTest;
        @Test
        public void dd(){
                try {
                TNavigationExample query=new TNavigationExample();
                TNavigationExample.Criteria criteria=query.createCriteria();
                criteria.andNavigationNameLike("%"+"材"+"%");
                List<TNavigation> list=navigationMapper.selectByExample(query);
                tryTest.listen(list);
                }catch (Exception e){
                        e.printStackTrace();
                }
        }


}
