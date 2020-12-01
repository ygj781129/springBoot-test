package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.pojo.Student;
import com.example.demo.service.DataSourceTest;
import com.example.demo.swagger.ApiJsonObject;
import com.example.demo.swagger.ApiJsonProperty;
import com.example.demo.util.JsonFormat;
import com.example.demo.vo.KeyValVo;
import com.example.demo.vo.SSSVo;
import com.google.common.collect.Lists;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by fb on 2020/6/4
 */

@RestController
@RequestMapping("/sss")
@Api(value = "用户信息", tags = { "用户信息" })
public class SwaggerController {

        @Autowired
        private DataSourceTest dataSourceTest;

        @GetMapping("/findList")
        @ApiOperation(value = "获取用户信息", notes = "通过用户ID获取用户信息")
        @ApiImplicitParams({
               @ApiImplicitParam(name = "id", value = "用户ID", required = true,dataType = "int",paramType = "query")
                //@ApiImplicitParam(name = "str", value = "用户str", required = true, dataType = "String",paramType = "path")
        })
        @ApiResponses({

                @ApiResponse(code = 200, message = "ok", response= SSSVo.class),
                //@ApiResponse(code = 200, message = "ok", response=Student.class)
        })
        public JsonFormat findXJBname(@RequestParam(value = "id",required = true)Integer id ){
                id=id==null?0:id;

                String aa="fdfdfdfdfdf"+id;
                Student student=new Student();
                student.setName("拿拿");
                Student student2=new Student();
                student2.setName("咪咪");
                List<Student> list= Lists.newArrayList();
                list.add(student);
                list.add(student2);
                SSSVo vo=new SSSVo();
                vo.setStudent(student);
                vo.setTag("ffff=111");
                return JsonFormat.success(vo);
        }

        @PostMapping("/findModel")
        //@ApiIgnore
        @ApiOperation(value = "叽叽歪歪", notes = "妈了个逼的")
        public JsonFormat findModel(@RequestBody List<Student> list){

                String aa="fdfdfdfdfdf"+list.get(0).getName();
                return JsonFormat.success(aa);
        }

        @ApiOperation(value = "拼接map", notes = "拼接map")
        @PostMapping("/findMap")
        @ApiImplicitParam(name = "map" , paramType = "body",examples = @Example({
                @ExampleProperty(value = "{'ids':[1111,wwwwwww]}", mediaType = "application/json")
        }))
        public JsonFormat del(@RequestBody Map<String,List<String>> map){
                int count=0;
                List ids=null;
                if(map.containsKey("ids")) {
                        ids = (ArrayList)map.get("ids");
                }
                List<Long> list= JSON.parseArray(JSON.toJSONString(ids),Long.class);
                String result = StringUtils.join(list, ",");
                String aa=result;
                return JsonFormat.success(aa);
        }
}
