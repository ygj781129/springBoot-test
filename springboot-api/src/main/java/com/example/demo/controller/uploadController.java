package com.example.demo.controller;

import com.example.demo.util.FileValidUtil;
import com.example.demo.util.JsonFormat;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.UUID;

/**
 * Created by Administrator on 2019/9/25.
 */
@Controller
@RequestMapping("/upload")
public class uploadController {

    @Value("${save.imagePath}")
    private String saveImagePath;

    /**
     * 这个是页面的跳转啊 这个里面不能写 @RestController 必须是@Controller
     * 因为这个@RestController 好像是处理json用的
     * 也不要加什么  @ResponseBody 啥的就按照下面那么写
     * @return
     */
    @RequestMapping(value="/file")
    public String testF2F() {
        return "index";
    }

    @RequestMapping(value="/uu")
    public String uu() {
        return "uploadFile";
    }

    /**
     * 实现文件上传
     * */
    @RequestMapping("fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("fileName") MultipartFile file){

        try {
            FileValidUtil.CheckFile(file,1024l);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(file.isEmpty()){
            return "false";
        }
        //获得文件的原始名称
        String fileName = file.getOriginalFilename();
        //文件大小
        int size = (int) file.getSize();
        //文件.的位置
        int i = fileName.lastIndexOf(".");
        //获得后缀名
        String subString = fileName.substring(i);
        //文件的保存路径+/+文件名+.后缀名
        String newfileName = UUID.randomUUID().toString().replaceAll("-", "");
        if(size>1048576000){
            return "图片大小不能大于100M";
        }
        System.out.println(fileName + "-->" + size);
        //文件的保存路径+/+文件名+.后缀名
        File dest = new File(saveImagePath + "/" + newfileName+"."+subString);
        String accessPath=saveImagePath + "/" + newfileName+"."+subString;
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            return accessPath;
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        }
    }

    /**
     * 文件下载
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/download")
    public String downLoad(HttpServletResponse response, @RequestParam("id") String id) throws UnsupportedEncodingException {
        String filename=id;
        String filePath = "E:/tomcat/webapps/picture/img" ;
        File file = new File(filePath + "/" + filename);
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            // response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" +   java.net.URLEncoder.encode(filename,"UTF-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;
            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download---" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }





}
