package com.example.tokenbucketredis.controller;

import com.example.tokenbucketredis.config.Constant;
import com.example.tokenbucketredis.config.LimitingRequired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.tokenbucketredis.vo.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by fb on 2020/10/21
 */
@Controller
@RestController
public class TController {
        @Autowired
        private RedisTemplate redisTemplate;

        @RequestMapping(value = "test")
        @LimitingRequired(required = true)
        public ResponseEntity test() {
                ListOperations listOperations = redisTemplate.opsForList();
                return new ResponseEntity<>("test:队列长度:" + listOperations.size(Constant.TOKEN_BUCKET_KEY));
        }
}
