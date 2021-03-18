package com.example.demo.service;

import com.example.demo.pojo.UserInfo;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.HashMap;

import static org.junit.Assert.assertNull;

/**
 * Created by fb on 2021/2/1
 */
@Slf4j
@Service
public class CaffeineCacheServiceImpl {

        /**
         * 模拟数据库存储数据
         */
        private HashMap<Integer, UserInfo> userInfoMap = new HashMap<>();

        @Autowired
        Cache<String, Object> caffeineCache;


        public void addUserInfo(UserInfo userInfo) {
                log.info("create");
                userInfoMap.put(userInfo.getId(), userInfo);
                // 加入缓存
                caffeineCache.put(String.valueOf(userInfo.getId()),userInfo);

        }


        public UserInfo getByName(Integer id) {
                // 先从缓存读取
                //caffeineCache.getIfPresent(id);
                UserInfo userInfo = (UserInfo) caffeineCache.asMap().get(String.valueOf(id));
                if (userInfo != null){
                        return userInfo;
                }
                // 如果缓存中不存在，则从库中查找
                log.info("get");
                userInfo = userInfoMap.get(id);
                // 如果用户信息不为空，则加入缓存
                if (userInfo != null){
                        caffeineCache.put(String.valueOf(userInfo.getId()),userInfo);
                }
                return userInfo;
        }


        public UserInfo updateUserInfo(UserInfo userInfo) {
                log.info("update");
                if (!userInfoMap.containsKey(userInfo.getId())) {
                        return null;
                }
                // 取旧的值
                UserInfo oldUserInfo = userInfoMap.get(userInfo.getId());
                // 替换内容
                if (!StringUtils.isEmpty(oldUserInfo.getAge())) {
                        oldUserInfo.setAge(userInfo.getAge());
                }
                if (!StringUtils.isEmpty(oldUserInfo.getName())) {
                        oldUserInfo.setName(userInfo.getName());
                }
                if (!StringUtils.isEmpty(oldUserInfo.getSex())) {
                        oldUserInfo.setSex(userInfo.getSex());
                }
                // 将新的对象存储，更新旧对象信息
                userInfoMap.put(oldUserInfo.getId(), oldUserInfo);
                // 替换缓存中的值
                caffeineCache.put(String.valueOf(oldUserInfo.getId()),oldUserInfo);
                return oldUserInfo;
        }


        public void deleteById(Integer id) {
                log.info("delete");
                userInfoMap.remove(id);
                // 从缓存中删除
                caffeineCache.asMap().remove(String.valueOf(id));
        }




        //****************************************下面的是手动和同步的******************************************************
        /**
         * 同步填充
         */
        LoadingCache<Integer, UserInfo> loadingCache = Caffeine.newBuilder()
                .maximumSize(10_000)
                .expireAfterWrite(10000, TimeUnit.MINUTES)
                .build(key -> createExpensiveGraph(key));
        public UserInfo findddd(Integer key) {
                //String key = "name1";

                // 采用同步方式去获取一个缓存和上面的手动方式是一个原理。在build Cache的时候会提供一个createExpensiveGraph函数。
                // 查询并在缺失的情况下使用同步的方式来构建一个缓存
                UserInfo graph = loadingCache.get(key);

                // 获取组key的值返回一个Map
                List<Integer> keys = new ArrayList<>();
                keys.add(1);
                keys.add(2);
                Map<Integer, UserInfo> graphs = loadingCache.getAll(keys);
                return graph;
        }
        public UserInfo createExpensiveGraph(Integer key) {
                System.out.println("缓存不存在或过期，调用了createExpensiveGraph方法获取缓存key的值");
                if (key.equals("name")) {
                        throw new RuntimeException("调用了该方法获取缓存key的值的时候出现异常");
                }
                UserInfo userInfo=userInfoMap.get(key);
                return  userInfo;
        }

        /**
         * 手动填充
         */
        Cache<Integer, UserInfo> manualCache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(10_000)
                .build();
        public UserInfo find88d(Integer key) {
                UserInfo graph = null;

                // 根据key查询一个缓存，如果没有返回NULL
                graph = manualCache.getIfPresent(key);
                // 根据Key查询一个缓存，如果没有调用createExpensiveGraph方法，并将返回值保存到缓存。
                // 如果该方法返回Null则manualCache.get返回null，如果该方法抛出异常则manualCache.get抛出异常
                graph = manualCache.get(key, k -> createExpensiveGraph(k));
                // 将一个值放入缓存，如果以前有值就覆盖以前的值
                manualCache.put(key, graph);
                // 删除一个缓存
                manualCache.invalidate(key);

                ConcurrentMap<Integer, UserInfo> map = manualCache.asMap();
                System.out.println(map.toString());
                return graph;

        }
}
