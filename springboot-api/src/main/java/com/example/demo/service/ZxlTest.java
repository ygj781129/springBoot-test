package com.example.demo.service;

import com.example.demo.SpringbootApiApplication;
import com.example.demo.vo.KeyValVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by fb on 2020/5/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApiApplication.class)
public class ZxlTest {

        private Logger logger = LoggerFactory.getLogger(ZxlTest.class);
        @Autowired
        private AsyncTaskServiceImpl asyncTaskServiceImpl;

        @Test
        public void ddd(){
        System.out.println("fdfdsghfdhgfhfdhdfh");
        }




        @Test
        public void testAsync(){
        String msg="测试测试";
                System.out.println(Thread.currentThread().getName()+":"+msg);
                asyncTaskServiceImpl.executeAsyncTask(msg);
                try {
                        long start = System.currentTimeMillis();
                        Future<Long> future = asyncTaskServiceImpl.asyncInvokeReturnFuture();
                        Future<Long> future2 = asyncTaskServiceImpl.asyncInvokeReturnFuture2();
                        Future<Long> future3 = asyncTaskServiceImpl.asyncInvokeReturnFuture3();
                        Long l1 = future.get();//5000
                        Long l2 = future2.get();//3500
                        Long l3 = future3.get();//2500
                        System.out.println(Thread.currentThread().getName()+"*************************************");
                        System.out.println(Thread.currentThread().getName()+(l1+l2+l3 ));
                        System.out.println(Thread.currentThread().getName()+"-------------------------------------");
                        System.out.println(Thread.currentThread().getName()+(System.currentTimeMillis()-start));
                        System.out.println(Thread.currentThread().getName()+"+++++++++++++++++++++++++++++++++++++");
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        @Test
        public void ddddddd() {
                long currentTimeMillis = System.currentTimeMillis();
                //System.out.println("添加令牌---线程：" + Thread.currentThread().getName() + ",执行时间:" + currentTimeMillis + "-" + port);
                //listOperations.leftPush(Constant.TOKEN_BUCKET_KEY, DateUtils.dateToString(new Date(currentTimeMillis)));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date= new Date(currentTimeMillis);
                String str=sdf.format(date);
                System.out.println("*******"+ str);

        }

}
