package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.helper.CommonMethed;
import com.example.demo.pojo.HMedicalExamine;
import com.example.demo.pojo.PathEntity;
import com.example.demo.pojo.TimeTest;
import com.example.demo.service.ThhServicesImpl;
import com.example.demo.util.JsonFormat;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2019/8/30.
 */
@RestController
@RequestMapping("/testDate")
public class RedisController {
    @Value("${save.imagePath}")
    private String saveImagePath;
//    @Autowired
//    private PathEntity pathEntity;
//    @GetMapping("pathStr66")
//    public String getSTRpathEntity(){
//        return pathEntity+"";
//    }
    @Autowired
    private ThhServicesImpl thhServices;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
//    @GetMapping("pathStr")
//    public String getSTR(){
//        return imagePath;
//    }

    @GetMapping("/getDate")
    public String getDateString(){
        Date date= CommonMethed.getCurDate();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "当前时间"+sdf.format(date);
    }

    /**
     * 添加缓存
     * condition是触发缓存的条件
     * 第一次调用方法时将返回的也就是return的内容存入缓存中
     * 第二次调用是就直接调用缓存中的内容
     * @param
     * @return
     *  condition = "#openid.length > 3"
     *  new ArrayList(set)
     */
    //@Cacheable(cacheNames = "product3",key = "9993")
    @GetMapping("/getEnty")
    public JsonFormat getEnty(@RequestParam(value="tag", required=true) String tag,
                              @RequestParam(value="productId", required=false) String productId)throws Exception{
        //S;

//        String s= stringRedisTemplate.opsForValue().get("product3::1");
//        List<HMedicalExamine> list = JSON.parseArray(s,HMedicalExamine.class);
        //List<HMedicalExamine> list=thhServices.getEnty();
        //System.out.println("fff"+object.toString());
//        String s="product333::gg-878787";
//        String str=s.substring(s.lastIndexOf(":")+1);
//        List<HMedicalExamine> list=thhServices.ff(str);
//        System.out.println("88888"+list.toString());
        Set<String> set=stringRedisTemplate.keys("product3::*");
        List<String> list=new ArrayList<String>(set);
        return   JsonFormat.success(list.get(0));
    }



    @GetMapping("/getEntyuu")
    public JsonFormat getEnty33(@RequestParam(value="tag", required=true) String tag,
                                @RequestParam(value="productId", required=false) String productId){

        List<HMedicalExamine> list=thhServices.getEnty(tag,productId);
        return  JsonFormat.success(list);
    }
    /**
     * 更新缓存
     * cacheNames = "product",key = "123" 这个要跟 @Cacheable中的一致
     * @return
     */
//    @CachePut(cacheNames = "product",key = "123")
//    @GetMapping("/getEnty2")
//    public JsonFormat getEnty2(){
//        List<HMedicalExamine> list=thhServices.getEnty();
//        return  JsonFormat.success(list);
//    }

    /**
     * 删除缓存
     */
    @CacheEvict(cacheNames = "product",key = "123")
    @GetMapping("/getEnty3")
    public void getEnty3(){
        System.out.println("my-redis-cache2槽里的所有缓存被清除....");
    }

    /**
     * redistemplate
     * @return
     */
//    @GetMapping("/getEntyff")
//    public JsonFormat getEntyff(){
//        String str="fff";
//        List<HMedicalExamine> list=thhServices.getEnty();
//        redisTemplate.opsForValue().set(str,list);
//        return  JsonFormat.success(list);
   //


    @GetMapping("/uuu")
    public JsonFormat uuu(){
        List<TimeTest> list=thhServices.getgett();
        return  JsonFormat.success(list);
    }


    @GetMapping("/findXJBnam22e")
    public JsonFormat findXJ55Bname(){
        String ff="jfkldjfk/jk.jfl,kdj|gagkd/*-aghkksdjgl|76u*tyrj/j";
        List<String> list = Arrays.asList(ff.split("\\|"));
        //this.redisUtil.set("11", JSON.toJSONString(list));
        return JsonFormat.success(list);
    }


    public void dd(){
        String msg="测试测试";
        thhServices.executeAsyncTask(msg);
    }

    public static void main(String[] args) {


    }
}
