package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.AcctUserMapper;
import com.example.demo.mapper.TimeTestMapper;
import com.example.demo.pojo.AcctUser;
import com.example.demo.pojo.TimeTest;
import com.example.demo.pojo.TimeTestExample;
import com.example.demo.service.DataSourceTest;
import com.example.demo.service.ImgAddImg;
import com.example.demo.util.JsonFormat;
import com.example.demo.util.RecodeUtil;
import com.example.demo.vo.KeyValVo;
import com.example.demo.vo.Svo;
import com.example.demo.vo.fg;
import com.github.pagehelper.PageHelper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2019/9/29.
 */
@RestController
@RequestMapping("/xjb")
public class XjbTestController {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private AcctUserMapper userMapper;
    @Autowired
    private TimeTestMapper timeTestMapper;

    @GetMapping("/findXJB")
    public JsonFormat findxJB(@RequestParam("id")Long id){
        AcctUser user=userMapper.findById(id);
        return JsonFormat.success(user);
    }
    @GetMapping("/findXJBname")
    public JsonFormat findXJBname(@RequestParam("name")String name){
        String user="价格间隔水电费解放路";
        return JsonFormat.success(user);
    }

    @GetMapping("/findXJBnam22e")
    public JsonFormat findXJ55Bname(){
        String ff="jfkldjfk/jk.jfl,kdj|gagkd/*-aghkksdjgl|76u*tyrj/j";
        List<String> list = Arrays.asList(ff.split("\\|"));
        return JsonFormat.success(list);
    }

    /**
     *
     * @param sdate
     * @param edate
     * @return
     * @throws Exception
     */

    @GetMapping("/findXJfname")
    public JsonFormat findXJfname(@RequestParam(value = "sdate",required = true)Integer sdate,
                                  @RequestParam(value = "edate",required = true)Integer edate)throws Exception{
        TimeTestExample query=new TimeTestExample();
        TimeTestExample.Criteria criteria=query.createCriteria();
        /**
         * 这个地方我有必要注释一下
         * mysql 的limit分页的 是从0开始  不包含左边 （左开右闭）
         * 如果是直接用sql语句写的纳闷就用beginIndex=0的这个
         * 如果用PageHelper 就用beginIndex=1的这个
         */
        int beginIndex = sdate<=0?1:sdate;//PageHelper
        int endIndex = edate * edate;
//        int beginIndex = (cpage - 1) * pageSize; //sql
//        int endIndex = cpage * pageSize;
        query.setOrderByClause("id asc");//排序码升序
        PageHelper.startPage(beginIndex,endIndex);//分页
        List<TimeTest> list=timeTestMapper.selectByExample(query);
        return JsonFormat.success(list);
    }

    @PostMapping("/gg")
    public void findxJB(@RequestBody Svo svo){

        List<fg>list=svo.getKeyValVos();

        for (fg valVo:list){
            System.out.println(valVo.getKey()+"--------"+valVo.getVal()+"---"+svo.getDid());
        }
    }

    @Autowired
    private DataSourceTest dataSourceTest;
    @GetMapping("/trtr")
    public String trtr(){
        try {

            return "成功";
        }catch (Exception e){
e.printStackTrace();
            return "失败";
        }

    }



    @GetMapping("/u")
    public void reCode(HttpServletResponse response) {
        RecodeUtil.creatRrCode("www.baidu.com", 200, 200, response);
//这里去掉https:扫描二维码返回是字符串
    }
    @GetMapping("/test")
    public void dowanload(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //二维码中包含的信息
        String content = "姓名:十二余\n博客：https://www.cnblogs.com/jing5464";
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        // 指定编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        // 指定纠错级别(L--7%,M--15%,Q--25%,H--30%)
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 编码内容,编码类型(这里指定为二维码),生成图片宽度,生成图片高度,设置参数
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 200, 200, hints);
        //设置请求头
        response.setHeader("Content-Type","application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + "十二余的二维码.png");
        OutputStream outputStream = response.getOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "png", outputStream);
        outputStream.flush();
        outputStream.close();
    }

        @GetMapping("/ii")
        public void reC55ode(HttpServletResponse response,String path)  throws IOException, WriterException {
            BufferedImage code = ImgAddImg.createImage("https://my.oschina.net/kevin2kelly", null, false);
            ImgAddImg.rrrrr( path, code,response);
        }
    }
