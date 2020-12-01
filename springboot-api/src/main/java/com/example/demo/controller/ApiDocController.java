package com.example.demo.controller;

import com.example.demo.service.DataSourceTest;
import com.example.demo.util.JsonFormat;
import com.example.demo.vo.KeyValVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
