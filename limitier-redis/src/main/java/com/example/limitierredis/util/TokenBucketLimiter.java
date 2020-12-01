package com.example.limitierredis.util;

import com.example.limitierredis.util.RedisUtil;
import com.example.limitierredis.vo.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by fb on 2020/10/21
 */
@Component
public class TokenBucketLimiter {

        @Autowired
        //使用项目中的redis客户端即可
        private RedisUtil redisManager;


        public int limiter(String str){
                String key=Constant.TOKEN_BUCKET_KEY+":"+str;
                if(redisManager.hasKey(key)){
                        Long n=Long.valueOf(redisManager.get(key).toString());
                        if(n>=0){
                                redisManager.decr(key,1);
                             return 1;
                        }else {
                                return -1;
                        }
                }else {
                        redisManager.set(key,Constant.TOKEN_BUCKET_SIZE-1);
                }
                return 2;
        }


        public void addTokenBucket(String key) {
                ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(1);
                Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                                Long size=Long.valueOf(redisManager.get(key).toString());
                                if (size.compareTo(Constant.TOKEN_BUCKET_SIZE) == -1) {
                                        long currentTimeMillis = System.currentTimeMillis();
                                        System.out.println("添加令牌---线程：" + Thread.currentThread().getName() + ",执行时间:" + currentTimeMillis );
                                        redisManager.incr(key);
                                        redisManager.expire(key, 600);
                                }
                        }
                };
                //定时器间隔设置成100毫秒，每100ms添加一个令牌，即限制了每秒钟请求次数最多有10个请求+预存的10的令牌（如果有突发流量，每秒钟最多请求次数接近10）
                scheduled.scheduleAtFixedRate(runnable, 1, 1000, TimeUnit.MILLISECONDS);
        }
}
