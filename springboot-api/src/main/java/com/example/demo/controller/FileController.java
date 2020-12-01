//package com.example.demo.controller;
//
//
//import cn.hutool.core.util.StrUtil;
//import com.example.demo.pojo.FileDocument;
//import com.example.demo.pojo.ResponseModel;
//import com.example.demo.service.FileService;
//import com.example.demo.util.MD5Util;
//import com.mongodb.client.MongoDatabase;
//import com.mongodb.client.gridfs.GridFSBucket;
//import com.mongodb.client.gridfs.GridFSBuckets;
//import com.mongodb.client.gridfs.GridFSDownloadStream;
//import com.mongodb.client.gridfs.model.GridFSFile;
//import com.mongodb.gridfs.GridFSDBFile;
//import org.apache.tomcat.util.http.fileupload.IOUtils;
//import org.bson.types.ObjectId;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.mongodb.MongoDbFactory;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.gridfs.GridFsResource;
//import org.springframework.data.mongodb.gridfs.GridFsTemplate;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.security.NoSuchAlgorithmException;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//
///**
// * Created by fb on 2020/7/14
// */
//
//@RestController
//@RequestMapping("/file")
//public class FileController {
//        @Autowired
//        private FileService fileService;
//
//        @Value("${server.address}")
//        private String serverAddress;
//
//        @Value("${server.port}")
//        private String serverPort;
//
//        @RequestMapping(value = "/")
//        public String index(Model model) {
//                // 展示最新二十条数据
//                model.addAttribute("files", fileService.listFilesByPage(0, 20));
//                return "index";
//        }
//
//        /**
//         * 分页查询文件
//         */
//        @GetMapping("files/{pageIndex}/{pageSize}")
//        @ResponseBody
//        public List<FileDocument> listFilesByPage(@PathVariable int pageIndex, @PathVariable int pageSize) {
//                return fileService.listFilesByPage(pageIndex, pageSize);
//        }
//
//        /**
//         * 获取文件片信息
//         * 下载文件
//         */
//        @GetMapping("files/{id}")
//        @ResponseBody
//        public ResponseEntity<Object> serveFile(@PathVariable String id) throws UnsupportedEncodingException {
//
//                Optional<FileDocument> file = fileService.getFileById(id);
//
//                if (file.isPresent()) {
//                        return ResponseEntity.ok()
//                                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=" + new String(file.get().getName().getBytes("utf-8"),"ISO-8859-1"))
//                                .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
//                                .header(HttpHeaders.CONTENT_LENGTH, file.get().getSize() + "").header("Connection", "close")
//                                .body(file.get().getContent());
//                } else {
//                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File was not fount");
//                }
//
//        }
//
//        /**
//         * 在线显示文件
//         */
//        @GetMapping("/view")
//        @ResponseBody
//        public ResponseEntity<Object> serveFileOnline(@RequestParam("id") String id) {
//                Optional<FileDocument> file = fileService.getFileById(id);
//                if (file.isPresent()) {
//                        return ResponseEntity.ok()
//                                .header(HttpHeaders.CONTENT_DISPOSITION, "fileName=" + file.get().getName())
//                                .header(HttpHeaders.CONTENT_TYPE, file.get().getContentType())
//                                .header(HttpHeaders.CONTENT_LENGTH, file.get().getSize() + "").header("Connection", "close")
//                                .header(HttpHeaders.CONTENT_LENGTH , file.get().getSize() + "")
//                                .body(file.get().getContent());
//                } else {
//                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File was not found");
//                }
//
//
//        }
//
//        /**
//         * 上传
//         */
//        @PostMapping("/")
//        public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
//
//                try {
//                        FileDocument fileDocument = new FileDocument();
//                        fileDocument.setName(file.getOriginalFilename());
//                        fileDocument.setSize(file.getSize());
//                        fileDocument.setContentType(file.getContentType());
//                        fileDocument.setUploadDate(new Date());
//                        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//                        fileDocument.setSuffix(suffix);
//                        fileDocument.setMd5(MD5Util.getMD5(file.getInputStream()));
//                        //将文件存入gridFs
//                        String gridfsId = fileService.uploadFileToGridFS(file.getInputStream() , file.getContentType());
//                        fileDocument.setGridfsId(gridfsId);
//                        fileDocument = fileService.saveFile(fileDocument);
//                        System.out.println(fileDocument);
//                } catch (IOException | NoSuchAlgorithmException ex) {
//                        ex.printStackTrace();
//                        redirectAttributes.addFlashAttribute("message", "Your " + file.getOriginalFilename() + " is wrong!");
//                        return "redirect:/";
//                }
//
//                redirectAttributes.addFlashAttribute("message",
//                        "You successfully uploaded " + file.getOriginalFilename() + "!");
//
//                return "redirect:/";
//        }
//
//        /**
//         * 上传接口
//         */
//        @PostMapping("/upload")
//        @ResponseBody
//        public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
//                FileDocument returnFile = null;
//                try {
//                        FileDocument fileDocument = new FileDocument();
//                        fileDocument.setName(file.getOriginalFilename());
//                        fileDocument.setSize(file.getSize());
//                        fileDocument.setContentType(file.getContentType());
//                        fileDocument.setUploadDate(new Date());
//                        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//                        fileDocument.setSuffix(suffix);
//                        fileDocument.setMd5(MD5Util.getMD5(file.getInputStream()));
//                        //将文件存入gridFs
//                        String gridfsId = fileService.uploadFileToGridFS(file.getInputStream() , file.getContentType());
//                        fileDocument.setGridfsId(gridfsId);
//                        returnFile = fileService.saveFile(fileDocument);
//                        String path = "//" + serverAddress + ":" + serverPort + "/view/" + returnFile.getId();
//                        return ResponseEntity.status(HttpStatus.OK).body(path);
//
//                } catch (IOException | NoSuchAlgorithmException ex) {
//                        ex.printStackTrace();
//                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
//                }
//
//        }
//
//        /**
//         * 删除文件
//         */
//        @GetMapping("/delete")
//        @ResponseBody
//        public ResponseModel deleteFile(@RequestParam("id") String id) {
//                ResponseModel model = ResponseModel.getInstance();
//                if(!StrUtil.isEmpty(id)){
//                        fileService.removeFile(id);
//                        model.setCode(ResponseModel.Success);
//                        model.setMessage("删除成功");
//                }else {
//                        model.setMessage("请传入文件id");
//                }
//                return model;
//        }
//
//
//
//
////************************************瞎几把写的下载************************************************************
//        @Autowired
//        private MongoTemplate mongoTemplate;
//
//        @Autowired
//        private GridFsTemplate gridFsTemplate;
//
//        @Autowired
//        private GridFSBucket gridFSBucket;
//        private MongoDbFactory mongoDbFactory;
//        public FileController(GridFsTemplate gridFsTemplate,MongoDbFactory mongoDbFactory) {
//                this.gridFsTemplate = gridFsTemplate;
//                this.mongoDbFactory = mongoDbFactory;
//        }
//
//        /**
//         * 下载
//         *
//         * @param fileId   文件id
//         * @param response
//         * @return
//         */
//        @RequestMapping(value = "/downloadFile")
//        public void downloadFile(@RequestParam(name = "id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
//                FileDocument fileDocument = mongoTemplate.findById(id , FileDocument.class );
//                Query gridQuery = new Query().addCriteria(Criteria.where("filename").is(fileDocument.getGridfsId()));
//                 //根据id查询文件
//                GridFSFile fsFile = gridFsTemplate.findOne(gridQuery);
//                //打开下载流对象
//                MongoDatabase db = mongoDbFactory.getDb();
//                GridFSBucket gridFSBucket = GridFSBuckets.create(db);
//                GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(fsFile.getObjectId());
//                //创建GridFsResource用于获取流对象
//                GridFsResource resource = new GridFsResource(fsFile, gridFSDownloadStream);
//                String fileName = fsFile.getFilename().replace(",", "");
//                //处理中文文件名乱码
//                if (request.getHeader("User-Agent").toUpperCase().contains("MSIE") ||
//                        request.getHeader("User-Agent").toUpperCase().contains("TRIDENT")
//                        || request.getHeader("User-Agent").toUpperCase().contains("EDGE")) {
//                        fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
//                } else {
//                        //非IE浏览器的处理：
//                        fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
//                }
//                // 通知浏览器进行文件下载
//                response.setContentType("multipart/form-data");
//                response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
//                IOUtils.copy(resource.getInputStream(), response.getOutputStream());
//        }
//
//
//
//
//}
