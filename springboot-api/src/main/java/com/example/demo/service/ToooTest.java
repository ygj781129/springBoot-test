package com.example.demo.service;

import com.example.demo.SpringbootApiApplication;
import com.example.demo.util.LunarSolarConverter;
import com.example.demo.vo.KeyValVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

/**
 * Created by fb on 2020/6/4
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApiApplication.class)
public class ToooTest  extends TryTest {
        @Autowired
        private DataSourceTest dataSourceTest;
//        @Test
//        public void tte(){
//                List<KeyValVo> list=dataSourceTest.findList();
//                list.stream().map(n->n.getVal()).forEach(System.out::println);
//        }

//
//        String beginDate="四月廿七";
//        char[] byear=beginDate.toCharArray();
//                for (char c:byear){
//
//        }

        public static void main(String[] args) {
                // 使用双冒号::来构造静态函数引用
                Function<String, Integer> fun = Integer::parseInt;
                Integer value = fun.apply("123");
                System.out.println(value);
                // 使用双冒号::来构造非静态函数引用
                String content = "Hello JDK8";
                Function<Integer, String> func = content::substring;
                String result = func.apply(1);
                System.out.println(result);

        }


        @Override
        public void open() {

        }

        @Override
        public void fff() {

        }
}
