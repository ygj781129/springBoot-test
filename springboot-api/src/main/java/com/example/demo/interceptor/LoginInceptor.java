//package com.example.demo.interceptor;
//
//
//import com.alibaba.fastjson.JSON;
//import com.example.demo.util.RedisUtil;
//import com.example.demo.vo.JsonFormat;
//import org.apache.commons.lang3.StringUtils;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
///**
// * 权限拦截
// */
//public class LoginInceptor implements HandlerInterceptor {
//    @Value("${JCYJ.USER.TOKEN}")
//    private String JCYJ_USER_TOKEN;
//    @Value("${EXPIRE.TIME}")
//    private int EXPIRE_TIME;
//    @Resource
//    private RedisUtil redisUtil;
//
//    //进入handler之前
//    @Override
//    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
//        String url=httpServletRequest.getRequestURI();
//        String ip=getIpAddr(httpServletRequest);
//        System.out.println(url+"************************************"+ip);
//        httpServletResponse.setContentType("application/json;charset=utf-8");
//        String token = httpServletRequest.getParameter("token");
//        if(StringUtils.isBlank(token)){
//            //未传参数
//            httpServletResponse.getWriter().write(JSON.toJSONString(JsonFormat.error("573","无权限访问")));
//            return false;
//        }else{
//            //判断token是否正确
//            try {
//
//                if(token.equals("123")){
//                    return true;
//                }
//                String key = JCYJ_USER_TOKEN+":"+token;
//                String value = this.redisUtil.get(key).toString();
//
//                if(StringUtils.isNotBlank(value)){
//                    if(token.equals(value)){
//                        redisUtil.expire(key,EXPIRE_TIME);
//                        return true;
//                    }
//                }
//                httpServletResponse.getWriter().write(JSON.toJSONString(JsonFormat.error("573","无权限访问")));
//            }catch (Exception e){
//                e.printStackTrace();
//                httpServletResponse.getWriter().write(JSON.toJSONString(JsonFormat.error("502","操作失败")));
//                return false;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//
//    }
//
//
//
//
//
//
//
//    public String getIpAddr(HttpServletRequest request) {
//        String ipAddress = request.getHeader("x-forwarded-for");
//        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
//            ipAddress = request.getHeader("Proxy-Client-IP");
//        }
//        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
//            ipAddress = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
//            ipAddress = request.getRemoteAddr();
//            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
//                //根据网卡取本机配置的IP
//                InetAddress inet = null;
//                try {
//                    inet = InetAddress.getLocalHost();
//                } catch (UnknownHostException e) {
//                    e.printStackTrace();
//                }
//                ipAddress = inet.getHostAddress();
//            }
//        }
//        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
//        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
//            if (ipAddress.indexOf(",") > 0) {
//                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
//            }
//        }
//        return ipAddress;
//    }
//    }
