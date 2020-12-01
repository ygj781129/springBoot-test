//package com.example.demo.interceptor;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//@Configuration
//public class MyWebMvcConfigurer  implements WebMvcConfigurer {
//        @Override
//        public void addViewControllers(ViewControllerRegistry registry) {
//                //好像是默认跳那个页面 我还没试验
//                registry.addViewController("/upload/file").setViewName("login");
//        }
//
//        @Override
//        public void addInterceptors(InterceptorRegistry registry) {
//                registry.addInterceptor(new LoginInceptor())
//               .addPathPatterns("/**")//拦截什么
//                //.excludePathPatterns("/login","/index.html","/tiger/login")
//               .excludePathPatterns("/redisttt/**","/upload/file","/sss/**");//不拦截什么
//        }
//}
