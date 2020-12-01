package com.example.demo.util;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Created by Administrator on 2019/9/27.
 * 多线程执行 配置
 */
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
    /*
     此处成员变量应该使用@Value从配置中读取
      */
    private int corePoolSize = 10;
    private int maxPoolSize = 200;
    private int queueCapacity = 10;
    @Bean
    public Executor taskExecutor() {
        /**
         * -核心线程数10：线程池创建时候初始化的线程数
         * -最大线程数20：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
         * -缓冲队列200：用来缓冲执行任务的队列
         * -允许线程的空闲时间60秒：当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
         * -线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
         * -线程池对拒绝任务的处理策略：这里采用了CallerRunsPolicy策略，
         * 当线程池没有处理能力的时候，该策略会直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，
         * 则会丢弃该任务
         */
        ThreadPoolTaskExecutor poolExecutor = new ThreadPoolTaskExecutor();
        poolExecutor.setCorePoolSize(10);
        poolExecutor.setMaxPoolSize(20);
        poolExecutor.setKeepAliveSeconds(60);
        poolExecutor.setQueueCapacity(200);
        poolExecutor.setThreadNamePrefix("zerahTask-");
        poolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return poolExecutor;
    }
}
