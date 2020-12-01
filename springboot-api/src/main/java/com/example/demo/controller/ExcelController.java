//package com.example.demo.controller;
//
//import com.example.demo.pojo.HMedicalExamine;
//import com.example.demo.pojo.personInfor;
//import com.example.demo.service.ThhServicesImpl;
//import com.example.demo.util.ExcelUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//import com.alibaba.fastjson.JSON;
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
///**
// * Created by Administrator on 2019/9/26.
// * Excel 导入导出
// */
//@RestController
//@RequestMapping("/excel")
//public class ExcelController {
//   @Autowired
//    private ThhServicesImpl thhServices;
//
//    /**
//     * 导出Excel
//     * @param response
//     * @throws IOException
//     */
////    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
////    public void exportExcel(HttpServletResponse response)  throws IOException {
////        //自己随便写要导出的数据
////        List<HMedicalExamine> resultList = thhServices.getEnty();
////
////        long t1 = System.currentTimeMillis();
////        ExcelUtils.writeExcel(response, resultList, HMedicalExamine.class);
////        long t2 = System.currentTimeMillis();
////        System.out.println(String.format("write over! cost:%sms", (t2 - t1)));
////    }
//
//    /**
//     * 导入Excel
//     * 格式按照那个  @ExcelColumn(value = "价格", col = 3) 中的value写
//     * value中的值会自己对应那个字段 顺序无所谓 真牛逼
//     * @param file
//     */
//    @RequestMapping(value = "/readExcel", method = RequestMethod.POST)
//    public void readExcel(MultipartFile file){
//        long t1 = System.currentTimeMillis();
//        List<personInfor> list = ExcelUtils.readExcel("", personInfor.class, file);
//        long t2 = System.currentTimeMillis();
//        System.out.println(String.format("read over! cost:%sms", (t2 - t1)));
//        list.forEach(
//                b -> System.out.println(JSON.toJSONString(b))
//        );
//        thhServices.save(list);//数据库里存或者修改
//    }
//}
