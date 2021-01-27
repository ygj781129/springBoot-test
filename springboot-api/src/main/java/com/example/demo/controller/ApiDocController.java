package com.example.demo.controller;

import com.example.demo.mapper.test02.personInforMapper;
import com.example.demo.pojo.personInfor;
import com.example.demo.service.DataSourceTest;
import com.example.demo.util.JsonFormat;
import com.example.demo.vo.KeyValVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.cursor.Cursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fb on 2020/6/4
 */

@RestController
@RequestMapping("/aaa")
public class ApiDocController {

        @Autowired
        private DataSourceTest dataSourceTest;



        /**
         * @api {get} /aaa/findList?sdate
         * @apiDescription  查询集合
         * @apiName 查询集合
         * @apiParam {Integer} sdate 用户的姓名
         *
         *
         *  @apiGroup index
         *  @apiError userNotFound  The <code>id</code>
         *      @apiSampleRequest /index
         */


        @GetMapping("/findList")
        @CrossOrigin
        public JsonFormat findXJBname(@RequestParam(value = "sdate",required = true)Integer sdate){
                List<KeyValVo> list=dataSourceTest.findList();
                return JsonFormat.success(list);
        }


        @Resource
        private com.example.demo.mapper.test02.personInforMapper personInforMapper;
        @GetMapping("foo")
        @Transactional
        public void scanFoo0() throws Exception {
                try (Cursor<personInfor> cursor = personInforMapper.scan()) {  // 1
                        cursor.forEach(foo -> {
                                System.out.println(foo.getName());
                        });                      // 2
                }
        }



        @PostMapping("foo1")
        public void scanFoo01(@RequestBody personInfor personInfor) throws Exception {
                List<personInfor> list=personInforMapper.scanll(personInfor.getName());
                list.forEach(foo -> {
                        System.out.println(foo.getName());
                });
        }


        @PostMapping("findd")
        public String finde(@RequestBody personInfor personInfor) {
                List<personInfor> list=dataSourceTest.finddd(personInfor.getName());
                if (list == null||list.isEmpty()) {
                        return "ssssss";
                }
                return "ok";
        }
}
