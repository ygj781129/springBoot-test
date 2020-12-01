//package com.example.demo.task;
//
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
///**
// * Created by fb on 2020/11/23
// */
//@Component
//@EnableScheduling
//@Async //多线程配置类
//public class TaskTest2 {
//        int n=0;
//        /**fixedRate:上一次开始执行时间点之后5秒再执行*/
//        //@Scheduled(fixedRate = 2000)
//        @Scheduled(cron = "0 32 16 * * ?")
//        public void run1() throws InterruptedException {
//                //Thread.sleep(4000);
//                n+=1;
//
//                try {
//                        throw new RuntimeException();
//                } catch (Exception e) {
//                        e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName()+"=====>>>>>222222222  {}-----"+n+"--------"+(System.currentTimeMillis()/1000));
//        }
//}
