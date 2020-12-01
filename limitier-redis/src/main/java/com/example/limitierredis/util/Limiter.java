package com.example.limitierredis.util;

import com.example.limitierredis.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by fb on 2020/10/21
 */
@Component
@Slf4j
public class Limiter {

        @Autowired
        //使用项目中的redis客户端即可
        private RedisUtil redisManager;

        public Boolean apply(String key, int limitCount, int limitPeriod, String descName) {
                double limitPerSec = limitCount * 1.0 / limitPeriod;
                Long n = redisManager.incr(key);
                if (limitPerSec < 1){
                        //如果qps小于1,不限制单位时间,限制单位时间的数量为1个,如qps=0.1,就是10s通过1个
                        if (n == 1L) {
                                //加上过期时间
                                redisManager.expire(key, limitPeriod);
                        } else if (n > 1L) {
                                log.info("限制访问key为 {}，描述为 [{}] 的接口",key, descName);
                               return false;
                        }
                }else{
                        //如果qps大于等于1,则反过来,不限制单位时间的数量,而是限制单位时间为1s,如qps=5,就是1s通过5个
                        if (n == 1L) {
                                //加上过期时间
                                redisManager.expire(key, 1);
                        } else if (n > limitPerSec) {
                                log.info("限制访问key为 {}，描述为 [{}] 的接口",key, descName);
                               return false;
                        }
                }
                return true;
        }


}
