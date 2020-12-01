package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by fb on 2020/6/15
 */
@Service
public  abstract class TryTest<T> implements Gggg{
        public TryTest() {
        }

        public abstract void open();

        public void listen(List<T> list)throws Exception{
                if(list == null || list.size() ==0){
                        throw new NullPointerException();
                }
                for(T model : list){
                        Class b = model.getClass();//获取Class对象
                        Field fromDeclaredField = b.getDeclaredField("navigationName");
                        fromDeclaredField.setAccessible(true);
                        Object obj = fromDeclaredField.get(model);
                        System.out.println("名字----"+obj);
                }
        }
}
