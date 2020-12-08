//package com.example.demo.task;
//
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
///**
// * Created by Administrator on 2019/9/27.
// * 定时任务
// */
//@Component
//@EnableScheduling
////@Async //多线程配置类
//public class TaskTest {
//
////    /**默认是fixedDelay 上一次执行完毕时间后执行下一轮*/
////    @Scheduled(cron = "0/2 * * * * *")
////    public void run() throws InterruptedException {
////        Thread.sleep(6000);
////        System.out.println(Thread.currentThread().getName()+"=====>>>>>使用cron  {}"+(System.currentTimeMillis()/1000));
////    }
//     int n=0;
//    /**fixedRate:上一次开始执行时间点之后5秒再执行*/
//    @Scheduled(fixedRate = 2000)
//    //@Scheduled(cron = "0 34 16 * * ?")
//    public void run1() throws InterruptedException {
//        Thread.sleep(6000);
//        n+=1;
//        System.out.println(Thread.currentThread().getName()+"=====>>>>>AAAA  {}-----"+n+"--------"+(System.currentTimeMillis()/1000));
//
//    }
//int m=0;
//    /**fixedDelay:上一次执行完毕时间点之后5秒再执行*/
//    //@Scheduled(fixedDelay = 2000)
//    @Scheduled(cron = "0 34 16 * * ?")
//    public void run2() throws InterruptedException {
//        //Thread.sleep(7000);
//            m+=1;
//        System.out.println(Thread.currentThread().getName()+"=====>>>>>BBBB  {}-----"+m+"--------"+(System.currentTimeMillis()/1000));
//    }
////
////    /**第一次延迟2秒后执行，之后按fixedDelay的规则每5秒执行一次*/
////    @Scheduled(initialDelay = 2000, fixedDelay = 5000)
////    public void run3(){
////        System.out.println(Thread.currentThread().getName()+"=====>>>>>使用initialDelay  {}"+(System.currentTimeMillis()/1000));
////    }
//
//}
