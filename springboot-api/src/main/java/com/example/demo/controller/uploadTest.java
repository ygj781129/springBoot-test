//package com.example.demo.controller;
//
//import cn.chinatsi.auction.pojo.TOnlineOperator;
//import cn.chinatsi.auction.service.TADAuctionUserServiceImpl;
//import cn.chinatsi.auction.vo.JsonFormat;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.jsoup.helper.StringUtil;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.annotation.Resource;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.OutputStream;
//import java.net.URLEncoder;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//
////import net.coobird.thumbnailator.Thumbnails;
//
///**
// * Created by Administrator on 2018/12/12.
// */
//@Controller
//@RequestMapping("/uploadTest")
//public class uploadTest {
//    @Value("${FILE_SAVE_PATH}")
//    private String FILE_SAVE_PATH;
//    @Value("${IMAGE_ACCESS_PATH}")
//    private String IMAGE_ACCESS_PATH;
//
//    @Resource
//    private TADAuctionUserServiceImpl tAuctionUserService;
//
//    /**
//     *#文件保存路径
//     FILE_SAVE_PATH=/usr/local/apache-tomcat-7.0.77/webapps/upload/report
//     #文件访问路径
//     IMAGE_ACCESS_PATH=http://upload.dataingbank.com/report
//     */
//
//    /**
//     *文件上传
//     * @param uploadFile 上传的文件名称原始的
//     * @param oldFileName 后来人为起的名
//     * @return
//     */
//    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
//    @ResponseBody
//    public JsonFormat uploadFile(MultipartFile uploadFile, String oldFileName){
//        try {
//            //获得文件的原始名称
//            String orginalFileName = uploadFile.getOriginalFilename();
////        if(StringUtil.isBlank(orginalFileName)){
////            return JsonFormat.error("文件名不能为空");
////        }
//            //最后的点的位置
//            int i = orginalFileName.lastIndexOf(".");
////        if(i == -1){
////            return JsonFormat.error("该文件的文件名不正确");
////        }
//            //获得后缀名
//            String subString = orginalFileName.substring(i);
////        if(!".jpg".equals(subString) && !".png".equals(subString)){
////            return JsonFormat.error("只能上传jpg或png类型的图片");
////        }
//            //获得文件大小
//            long size = uploadFile.getSize();
////        if(size>10485760){
////            return JsonFormat.error("图片大小不能大于10M");
////        }
//            //覆盖
//            if (oldFileName!=null&&StringUtil.isBlank(oldFileName)) {
//                String oldName = oldFileName.substring(oldFileName.lastIndexOf("/") + 1);
//                String fileExitedPath = FILE_SAVE_PATH + "/" + oldName;
//                File oldFile = new File(fileExitedPath);
//                if (oldFile.exists()) {
//                    oldFile.delete();
//                }
//            }
//
//            //用 当前日期+UUID作为文件名避免重名
////            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
////            String dateStr = sdf.format(new Date()).replaceAll("-", "");
////            String fileName = dateStr +UUID.randomUUID().toString().replaceAll("-", "");
//
//            //保存图片
//            String fileName = UUID.randomUUID().toString().replaceAll("-", "");;
//            //文件的保存路径+/+文件名+.后缀名
//            File newFile = new File(FILE_SAVE_PATH + "/" + fileName + subString);
//            //保存
//
//            //判断文件是否存在
//    //File isFiled = new File(FILE_SAVE_PATH+"/"+fileName+subString);
////            if(isFiled.exists()){
////                return JsonFormat.error("文件已存在");
////            }
////            //使用压缩工具压缩图片,并上传到服务器
////            if(size>=1048576 && size<10485760){
////                Thumbnails.of(uploadFile.getInputStream()).scale(0.2f).outputQuality(0.25f).toFile(isFiled);
////            }else{
////                uploadFile.transferTo(isFiled);
////            }
//            /**
//             * .pom
//             * <dependency>
//             <groupId>net.coobird</groupId>
//             <artifactId>thumbnailator</artifactId>
//             <version>0.4.8</version>
//             </dependency>
//             */
//            uploadFile.transferTo(newFile);
//            String accessPath = IMAGE_ACCESS_PATH + "/" + fileName + subString;
//            System.out.print("accessPath");
//            return new JsonFormat("0", "上传成功", accessPath);
//        }catch (Exception e){
//            e.printStackTrace();
//            return new JsonFormat("0", "失败", null);
//        }
//
//    }
//
///**
//    @RequestMapping("/imgDown")
//    @ResponseBody
//    public JsonFormat imgDown(String name,HttpServletRequest request,HttpServletResponse response) throws IOException{
//        String returnUrl = request.getSession().getServletContext().getRealPath("/images/")+name;
//        //Tomcat 安装目录
//        //String returnUrl = System.getProperty("catalina.home") + File.separator + "webapps" + File.separator + "images"+File.separator +name;
//        System.out.println(returnUrl);
//        //获取输入流
//        InputStream bis = new BufferedInputStream(new FileInputStream(new File(returnUrl)));
//        //假如以中文名下载的话
//        returnUrl = URLEncoder.encode(returnUrl,"UTF-8");
//        //设置文件下载头
//        response.addHeader("Content-Disposition", "attachment;filename=" + returnUrl);
//        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
//        response.setContentType("multipart/form-data");
//        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
//        int len = 0;
//        while((len = bis.read()) != -1){
//            out.write(len);
//            out.flush();
//        }
//        out.close();
//        return new JsonFormat("0", "下载成功", null);
//    }
//
//
//    @RequestMapping(value="/download",method=RequestMethod.GET)
//    public void download(@RequestParam(value="filename")String filename,
//                         HttpServletRequest request,
//                         HttpServletResponse response) throws IOException {
//        //模拟文件，myfile.txt为需要下载的文件
//        String path = request.getSession().getServletContext().getRealPath("statics\\upload")+"\\"+filename;
//        //获取输入流
//        InputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));
//        //转码，免得文件名中文乱码
//        filename = URLEncoder.encode(filename,"UTF-8");
//        //设置文件下载头
//        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
//        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
//        response.setContentType("multipart/form-data");
//        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
//        int len = 0;
//        while((len = bis.read()) != -1){
//            out.write(len);
//            out.flush();
//        }
//        out.close();
//    }
//
//
//**/
//    @RequestMapping("/downFile")
//    @ResponseBody
//    public void downFile(HttpServletRequest request,
//                         HttpServletResponse response) {
//        // 得到要下载的文件名
//        String fileName = request.getParameter("filename");
//        try {
//            // 获取上传文件的目录
//            ServletContext sc = request.getSession().getServletContext();
//            // 上传位置
//            String fileSaveRootPath = sc.getRealPath(FILE_SAVE_PATH);
//            // 得到要下载的文件
//            File file = new File(fileSaveRootPath  + "\\" + fileName);
//            // 设置响应头，控制浏览器下载该文件
//            response.setHeader("content-disposition", "attachment;filename="
//                    + URLEncoder.encode(fileName, "UTF-8"));
//            // 读取要下载的文件，保存到文件输入流
//            FileInputStream in = new FileInputStream(fileSaveRootPath + "\\" + fileName);
//            // 创建输出流
//            OutputStream out = response.getOutputStream();
//            // 创建缓冲区
//            byte buffer[] = new byte[1024];
//            int len = 0;
//            // 循环将输入流中的内容读取到缓冲区当中
//            while ((len = in.read(buffer)) > 0) {
//                // 输出缓冲区的内容到浏览器，实现文件下载
//                out.write(buffer, 0, len);
//            }
//            // 关闭文件输入流
//            in.close();
//            // 关闭输出流
//            out.close();
//            //下载完成，删除文件
//            file.delete();
//        } catch (Exception e) {
//
//        }
//    }
//
//    /**
//     *
//     * @param request
//     * @param response
//     * @param list
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping(value = "/OutExcel",method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
//    @ResponseBody
//    public JsonFormat OutExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        List<TOnlineOperator> list = tAuctionUserService.getAllOperator();
//        String message = "fail";
//        String dir = request.getSession().getServletContext().getRealPath("/output");
//        File fileLocation = new File(dir);
//        if (!fileLocation.exists()) {
//            boolean isCreated = fileLocation.mkdir();
//            if (!isCreated) {
//            }
//        }
//        String webUrl = request.getSession().getServletContext().getRealPath("/output");
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd mm-ss");
//        String createExcelname = df.format(new Date()) + "OutputExcel.xls";
//        String outputFile = webUrl + File.separator + createExcelname;
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        HSSFSheet sheet = workbook.createSheet();
//        workbook.setSheetName(0, "emddddddp");
//        HSSFRow row1 = sheet.createRow(0);
//        HSSFCell cell0 = row1.createCell(0, HSSFCell.CELL_TYPE_STRING);
//        HSSFCell cell1 = row1.createCell(1, HSSFCell.CELL_TYPE_STRING);
//        HSSFCell cell2 = row1.createCell(2, HSSFCell.CELL_TYPE_STRING);
//        HSSFCell cell3 = row1.createCell(3, HSSFCell.CELL_TYPE_STRING);
//
//        cell0.setCellValue("operatorId");
//        cell1.setCellValue("operatorName");
//        cell2.setCellValue("operatorPwd");
//        cell3.setCellValue("realName");
//        response.setContentType("text/html;charset=UTF-8");
//
//        for (int j = 0; j < list.size(); j++) {
//
//            TOnlineOperator empt = new TOnlineOperator();
//            empt = list.get(j);
//
//            HSSFRow row = sheet.createRow(j + 1);
//            HSSFCell c0 = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
//            HSSFCell c1 = row.createCell(1, HSSFCell.CELL_TYPE_STRING);
//            HSSFCell c2 = row.createCell(2, HSSFCell.CELL_TYPE_STRING);
//            HSSFCell c3 = row.createCell(3, HSSFCell.CELL_TYPE_STRING);
//
//            c0.setCellValue(empt.getOperatorId());
//            c1.setCellValue(empt.getOperatorName());
//            c2.setCellValue(empt.getOperatorPwd());
//            c3.setCellValue(empt.getRealName());
//
//        }
//        FileOutputStream fOut = new FileOutputStream(outputFile);
//        workbook.write(fOut);
//        fOut.flush();
//        fOut.close();
//        File f = new File(outputFile);
//        if (f.exists() && f.isFile()) {
//            try {
//                FileInputStream fis = new FileInputStream(f);
//                URLEncoder.encode(f.getName(), "utf-8");
//                byte[] b = new byte[fis.available()];
//                fis.read(b);
//                response.setCharacterEncoding("utf-8");
//                response.setHeader("Content-Disposition", "attachment; filename=" + createExcelname + "");
//                ServletOutputStream out = response.getOutputStream();
//                out.write(b);
//                out.flush();
//                out.close();
//                if (fis != null) {
//                    fis.close();
//                }
//                f.delete();
//                message = "success";
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return new JsonFormat("0", "导出成功", null);
//    }
//
//
//}
