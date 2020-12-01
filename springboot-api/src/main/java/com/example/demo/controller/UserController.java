package com.example.demo.controller;

/**
 * Created by Administrator on 2019/11/27.
 */

import com.example.demo.pojo.AcctUser;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

/**
 * 用户页面跳转
 */
@Controller
public class UserController {

    /**
     * 个人中心，需认证可访问
     */
    @RequestMapping("/user/index")
    public String add(HttpServletRequest request){
//        AcctUser bean=new AcctUser();
//        Object object= SecurityUtils.getSubject().getPrincipal();
//        try {
//            PropertyUtils.copyProperties(bean,object);
//            System.out.println(bean);
//            System.out.println("ffff"+bean.getName());
//        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//            e.printStackTrace();
//        }
        //String userName=SecurityUtils.getSubject().getPrincipal().toString();
        AcctUser bean=(AcctUser)SecurityUtils.getSubject().getPrincipal();
        request.setAttribute("userName",bean.getName());
        return "/user/index";
    }

    /**
     * 会员中心，需认证且角色为vip可访问
     */
    @RequestMapping("/vip/index")
    public String update(){
        return "/vip/index";
    }


    @RequestMapping("/goubi/index")
    public String goubi(){
        return "/goubi/index";
    }

}
