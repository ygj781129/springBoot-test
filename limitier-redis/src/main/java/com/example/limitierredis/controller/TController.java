package com.example.limitierredis.controller;

import com.example.limitierredis.util.Limiter;
import com.example.limitierredis.util.TokenBucketLimiter;
import com.example.limitierredis.vo.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fb on 2020/10/21
 */
@Controller
@RestController
public class TController {
        @Autowired
        private TokenBucketLimiter tokenBucketLimiter;
        @Autowired
        private Limiter limiter;
        @GetMapping("/f1")
        public int fff(){
                int flag=tokenBucketLimiter.limiter("f1");
                return flag;
        }
        @GetMapping("/tt")
        public void ttt(){
                String key= Constant.TOKEN_BUCKET_KEY+":"+"f1";
                tokenBucketLimiter.addTokenBucket(key);

        }

        @GetMapping("/f2")
        public Boolean fe(){
                Boolean b=limiter.apply("t1",100,1000000000,"刘伟");
                return b;
        }
}
