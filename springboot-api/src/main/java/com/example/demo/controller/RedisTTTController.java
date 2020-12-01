package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.util.JsonFormat;
import com.example.demo.util.RedisUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Administrator on 2019/8/30.
 */
@RestController
@RequestMapping("/redisttt")
public class RedisTTTController {
    @Resource
    private RedisUtil redisUtil;

    @GetMapping("/findXJBnam22e")
    public JsonFormat findXJ55Bname(){
        String ff="jfkldjfk/jk.jfl,kdj|gagkd/*-aghkksdjgl|76u*tyrj/j";
        List<String> list = Arrays.asList(ff.split("\\|"));
        //this.redisUtil.set("11", JSON.toJSONString(list));
        return JsonFormat.success(list);
    }

    /**
     *
     * @return
     */
    @GetMapping("/getRedis")
    public JsonFormat getRedis() {
        Object object = this.redisUtil.get("11");
//        List<String> list = castList(object, String.class);
//        list.forEach(System.out::println); // 输出：1 a
        List<String> list= JSON.parseArray(object.toString(),String.class);
        return JsonFormat.success(list);

    }

    public static <T> List<T> castList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }

}
