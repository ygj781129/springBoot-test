package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Created by fb on 2020/9/17
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnVo {
        private Integer status;
        private String msg;
        private Object data;


        public ReturnVo(Object data){
                this.status = HttpStatus.OK.value();
                this.msg = "请求成功";
                this.data = data;
        }

        public static ReturnVo success(Object data){
                return new ReturnVo(data);
        }

        public static ReturnVo success(){
                return new ReturnVo(null);
        }

        public static ReturnVo build(Integer status, String msg) {
                return new ReturnVo(status, msg, null);
        }

}
