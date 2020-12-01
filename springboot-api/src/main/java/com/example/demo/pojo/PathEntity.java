package com.example.demo.pojo;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 * Created by Administrator on 2019/8/30.
 */
@ConfigurationProperties(prefix = "upload")
@Getter
@Setter
public class PathEntity {

    private String imagePath;
    public String toString(){
    return "fdfd"+imagePath;
    }
}
