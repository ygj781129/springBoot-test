package com.example.demo.controller;

import com.example.demo.mongo.MongoTestDao;
import com.example.demo.pojo.MongoDer;
import com.example.demo.pojo.MongoTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fb on 2020/7/13
 */
@RestController
public class MongoDBTestController {
        @Autowired
        private MongoTestDao mtdao;

        @GetMapping(value="/test1")
        public void saveTest() throws Exception {
                MongoTest mgtest=new MongoTest();
                mgtest.setId(11);
                mgtest.setAge(33);
                mgtest.setName("ceshi");
                mgtest.setContent("在云栖社区上发起了cectionId{localValue:2, serverValue:");
                mtdao.saveTest(mgtest);
        }

        @GetMapping(value="/test2")
        public MongoTest findTestByName(){
                MongoTest mgtest= mtdao.findTestByName("ceshi");
                System.out.println("mgtest is "+mgtest);
                return mgtest;
        }

        @GetMapping(value="/test3")
        public void updateTest(){
                MongoTest mgtest=new MongoTest();
                mgtest.setId(11);
                mgtest.setAge(44);
                mgtest.setName("ceshi2");
                mtdao.updateTest(mgtest);
        }

        @GetMapping(value="/test4")
        public void deleteTestById(){
                mtdao.deleteTestById(11);
        }


}
