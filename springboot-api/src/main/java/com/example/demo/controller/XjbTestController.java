package com.example.demo.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.AcctUserMapper;
import com.example.demo.mapper.TimeTestMapper;
import com.example.demo.pojo.AcctUser;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.TimeTest;
import com.example.demo.pojo.TimeTestExample;
import com.example.demo.service.DataSourceTest;
import com.example.demo.service.ImgAddImg;
import com.example.demo.util.ImagesToBase64;
import com.example.demo.util.JsonFormat;
import com.example.demo.util.RecodeUtil;
import com.example.demo.vo.KeyValVo;
import com.example.demo.vo.Svo;
import com.example.demo.vo.fg;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFDrawing;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
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
    public JsonFormat findxJB(@RequestBody Svo svo){

        List<fg>list=svo.getKeyValVos();

        for (fg valVo:list){
            System.out.println(valVo.getKey()+"--------"+valVo.getVal()+"---"+svo.getDid());
        }
            return JsonFormat.success(svo);
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





            /**
             * 导出Excel
             * @param vo
             * @param response
             */
            @RequestMapping(value = "/export", method = RequestMethod.POST)
            @ResponseBody
            public void export(HttpServletResponse response){
                // 通过工具类创建writer，默认创建xls格式
                ExcelWriter writer = ExcelUtil.getWriter();
                ServletOutputStream out= null;
                try {
                List<Student> list= Lists.newArrayList();;
                Student s1=new Student();
                s1.setName("张三");
                s1.setSex("nan");
                s1.setAge(12);
                //System.out.println(":::::::::::::"+ImagesToBase64.Image2Base64("http://192.168.199.130:8081/img/11.jpg").toString());
                Student s2=new Student();
                s2.setName("李四");
                s2.setSex("女");
                s2.setAge(19);
                list.add(s1);
                list.add(s2);

                byte[] pictureData =ImagesToBase64.Image2Base64("http://192.168.199.130:8081/img/11.jpg");
                //写入图片
                writePic(writer, 0, 2, pictureData, HSSFWorkbook.PICTURE_TYPE_JPEG);



                //自定义标题别名
                writer.addHeaderAlias("name", "姓名");
                writer.addHeaderAlias("sex", "性别");
                writer.addHeaderAlias("age", "年龄");

                // 合并单元格后的标题行，使用默认标题样式
                //writer.merge(2, "标题");
                // 一次性写出内容，使用默认样式，强制输出标题
                writer.write(list, true);


                    //out为OutputStream，需要写出到的目标流
                    //response为HttpServletResponse对象
                    response.setContentType("application/vnd.ms-excel;charset=utf-8");
                    //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
                    response.setContentType("application/vnd.ms-excel;charset=utf-8");
                    String name = URLEncoder.encode("运营渠道数据", "UTF-8");
                    response.setHeader("Content-Disposition","attachment;filename="+ name +".xlsx");
                    out = response.getOutputStream();
                    writer.flush(out, true);
                } catch (IOException e) {
                    e.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                } finally {
                    // 关闭writer，释放内存
                    writer.close();
                }
                //此处记得关闭输出Servlet流
                IoUtil.close(out);
            }





    public void writePic(ExcelWriter writer, int x, int y, byte[] pictureData, int picType) {
        Sheet sheet = writer.getSheet();
        Drawing drawingPatriarch = sheet.createDrawingPatriarch();

        //设置图片单元格位置
        ClientAnchor anchor = drawingPatriarch.createAnchor(0, 0, 0, 0, x, y, x + 1, y + 1);
        //随单元格改变位置和大小
        anchor.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);

        //添加图片
        int pictureIndex = sheet.getWorkbook().addPicture(pictureData, picType);
        drawingPatriarch.createPicture(anchor, pictureIndex);
    }


}
