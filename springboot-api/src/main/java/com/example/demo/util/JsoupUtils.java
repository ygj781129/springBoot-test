package com.example.demo.util;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * created by zhangjingchuan on 2018/4/15
 * jsoup相关操作工具类
 */
public class JsoupUtils {

    /**
     * 根据请求地址返回json格式的响应结果
     * @param url
     * @return
     * @throws IOException
     */
    public static String jsonStr(String url)throws IOException {

        String body = null;
        try {
            Connection.Response res = Jsoup.connect(url)
                    .header("Accept", "*/*")
                    .header("Accept-Encoding", "gzip, deflate")
                    .header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                    .timeout(10000).ignoreContentType(true).execute();//.get();

            body = res.body();
        }catch (HttpStatusException e) {
            dataException(url);
        }

        return body;
    }

    /**
     * 根据请求地址返回json格式的响应结果
     * @param url
     * @return
     * @throws IOException
     */
    public static String jsonStr(String url, String json)throws IOException {

        String body = null;
       try {
           Connection.Response res = Jsoup.connect(url)
                   .header("Accept", "*/*")
                   .header("Accept-Encoding", "gzip, deflate")
                   .header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                   .header("Content-Type", "application/json;charset=UTF-8")
                   .header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                   .timeout(10000).requestBody(json).method(Connection.Method.POST).ignoreContentType(true).execute();//.get();
           body = res.body();
       }catch (HttpStatusException e) {
           dataException(url);
       }

        return body;
    }


    /**
     * 根据请求地址返回BufferedInputStream流
     * @param url
     * @return
     * @throws IOException
     */
    public static InputStream jsonInputStream(String url)throws IOException {
        BufferedInputStream body = null;
        try {
            Connection.Response res = Jsoup.connect(url)
                    .header("Accept", "*/*")
                    .header("Accept-Encoding", "gzip, deflate")
                    .header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                    .timeout(10000).method(Connection.Method.GET).ignoreContentType(true).execute();//.get();
            body = res.bodyStream();
        }catch (HttpStatusException e) {
            e.printStackTrace();
        }
        return body;
    }

    public static void dataException(String url) throws IOException {
        Connection conn = Jsoup.connect(url);
        Connection.Request req = conn.request();
        HttpURLConnection con = (HttpURLConnection)(req.proxy() == null ? req.url().openConnection() : req.url().openConnection(req.proxy()));

        int status = con.getResponseCode();

        System.out.println("异常信息：当前企业ID在OA中不存在");
        System.out.println("状态码："+ status);
    }
}
