package com.example.demo.util;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 *
 *
 * json格式化封装
 */
public class JsonFormat implements Serializable {
    /**
     * 对象成功
     */
    private final static String SUCCESS = "0";
    /**
     * 对象失败
     */
    private final static String ERROR = "1";

    /**
     * 没有数据
     */
    public final static String NO_DATA = "2";
    @ApiModelProperty(value = "返回码")
    public String code;
    @ApiModelProperty(value = "返回提示")
    public String message;
    @ApiModelProperty(value = "返回数据")
    public Object data;
    @ApiModelProperty(value = "总数")
    public int totalCount;


    public static JsonFormat error_noData(){
        return new JsonFormat(NO_DATA,"没有数据",null);
    }
    public static JsonFormat success(String message, Object data){
        return new JsonFormat(SUCCESS,message,data);
    }

    public static JsonFormat error(String message){
        return new JsonFormat(ERROR,message,null);
    }

    public static JsonFormat error(String code,String message){
        return new JsonFormat(code,message,null);
    }
    public static JsonFormat error(String message,Object data){
        return new JsonFormat(ERROR,message,data);
    }

    public static JsonFormat success(Object data){
        return new JsonFormat(SUCCESS,"操作成功",data);
    }
    public static JsonFormat success(Object data,int totalCount){
        return new JsonFormat(SUCCESS,"操作成功",totalCount,data);
    }
    public static JsonFormat success(){
        return new JsonFormat(SUCCESS,"操作成功",null);
    }
    public JsonFormat(String code, String message, Object data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public JsonFormat(String code, String message, int totalCount, Object data){
        this.code = code;
        this.message = message;
        this.data = data;
        this.totalCount = totalCount;
    }

    public JsonFormat() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
