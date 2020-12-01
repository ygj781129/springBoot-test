//package com.example.demo.service;
//
//import cn.hutool.core.io.IoUtil;
//import cn.hutool.core.util.IdUtil;
//import com.example.demo.mongo.FileRepository;
//import com.example.demo.pojo.FileDocument;
//import com.example.demo.pojo.FileModel;
//import com.google.common.collect.Lists;
//import com.google.common.primitives.Bytes;
//import com.mongodb.client.gridfs.GridFSBucket;
//import com.mongodb.client.gridfs.GridFSDownloadStream;
//import com.mongodb.client.gridfs.model.GridFSFile;
//import org.apache.commons.io.IOUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.mongodb.MongoDbFactory;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Field;
//import org.springframework.data.mongodb.gridfs.GridFsResource;
//import org.springframework.data.mongodb.gridfs.GridFsTemplate;
//import org.springframework.stereotype.Service;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
//
//import java.io.ByteArrayOutputStream;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.*;
//
///**
// * Created by fb on 2020/7/14
// */
//@Service
//public class FileService {
//        @Autowired
//        private MongoTemplate mongoTemplate;
//
//        @Autowired
//        private GridFsTemplate gridFsTemplate;
//
//        @Autowired
//        private GridFSBucket gridFSBucket;
//
//
//        /**
//         * 保存文件
//         * @param file
//         * @return
//         */
//
//        public FileDocument saveFile(FileDocument file) {
//                file = mongoTemplate.save(file);
//                return file;
//        }
//
//        /**
//         * 上传文件到Mongodb的GridFs中
//         * @param in
//         * @param contentType
//         * @return
//         */
//
//        public String uploadFileToGridFS(InputStream in , String contentType){
//                String gridfsId = IdUtil.simpleUUID();
//                //将文件存储进GridFS中
//                gridFsTemplate.store(in, gridfsId , contentType);
//                return gridfsId;
//        }
//
//
//        /**
//         * 删除文件
//         * @param id
//         */
//
//        public void removeFile(String id) {
//                //根据id查询文件
//                FileDocument fileDocument = mongoTemplate.findById(id , FileDocument.class );
//                if(fileDocument!=null){
//                        //根据文件ID删除fs.files和fs.chunks中的记录
//                        Query deleteFileQuery = new Query().addCriteria(Criteria.where("filename").is(fileDocument.getGridfsId()));
//                        gridFsTemplate.delete(deleteFileQuery);
//                        //删除集合fileDocment中的数据
//                        Query deleteQuery=new Query(Criteria.where("id").is(id));
//                        mongoTemplate.remove(deleteQuery,FileDocument.class);
//                }
//        }
//
//        /**
//         * 根据id查看文件
//         * @param id
//         * @return
//         */
//
//        public Optional<FileDocument> getFileById(String id){
//                FileDocument fileDocument = mongoTemplate.findById(id , FileDocument.class );
//                if(fileDocument != null){
//                        Query gridQuery = new Query().addCriteria(Criteria.where("filename").is(fileDocument.getGridfsId()));
//                        try {
//                                //根据id查询文件
//                                GridFSFile fsFile = gridFsTemplate.findOne(gridQuery);
//                                //打开流下载对象
//                                GridFSDownloadStream in = gridFSBucket.openDownloadStream(fsFile.getObjectId());
//                                if(in.getGridFSFile().getLength() > 0){
//                                        //获取流对象
//                                        GridFsResource resource = new GridFsResource(fsFile, in);
//                                        //获取数据
//                                        fileDocument.setContent(IoUtil.readBytes(resource.getInputStream()));
//                                        //fileDocument.setContent(this.toByteArray2(resource.getInputStream()));
//                                        return Optional.of(fileDocument);
//                                }else {
//                                        fileDocument = null;
//                                        return Optional.empty();
//                                }
//                        }catch (IOException ex){
//                                ex.printStackTrace();
//                        }
//                }
//                return Optional.empty();
//        }
//        public byte[] toByteArray(InputStream input) throws IOException {
//                        ByteArrayOutputStream output = new ByteArrayOutputStream();
//                        byte[] buffer = new byte[1024*4];
//                        int n = 0;
//                        int i = 0;
//                        List<Byte> returnList= Lists.newArrayList();
//                        while (-1 != (n = input.read(buffer))) {
//                                output.write(buffer, 0, n);
//                                i++;
//                                if (i >= 100) {// 假设读取100行
//                                        i = 0;
//
//                                        //List list= Arrays.asList(output.toByteArray());
//                                        List<Byte> list=new ArrayList();
//                                        for(byte b:output.toByteArray()){
//                                                list.add(Byte.valueOf(b));
//                                        }
//                                        System.out.println("-s--"+output.size());
//                                        returnList.addAll(list);
//                                        output.reset();
//                                }
//                        }
//                        byte[] bytes= Bytes.toArray(returnList);
//                        return bytes;
//        }
//        public  byte[] toByteArray2(InputStream input) throws IOException {
//                ByteArrayOutputStream output = new ByteArrayOutputStream();
//                byte[] buffer = new byte[1024*4];
//               int n = 0;
//                int i = 0;
//
//                while (-1 != (n = input.read(buffer))) {
//                        i++;
//                        System.out.println("-i--"+i);
//                        System.out.println("-s--"+output.size());
//                        output.write(buffer, 0, n);
//                        if (i >= 30000) {// 假设读取100行
//                                i = 0;
//
//                                break;
//                        }
//
//                         }
//                System.out.println("uuu"+n);
//        return output.toByteArray();
//        }
//
//
//        /**
//         * 分页列出文件
//         * @param pageIndex
//         * @param pageSize
//         * @return
//         */
//
//        public List<FileDocument> listFilesByPage(int pageIndex, int pageSize) {
//                Query query = new Query().with(Sort.by(Sort.Direction.DESC, "uploadDate"));
//                long skip = (pageIndex -1) * pageSize;
//                query.skip(skip);
//                query.limit(pageSize);
//                Field field = query.fields();
//                field.exclude("content");
//                List<FileDocument> files = mongoTemplate.find(query , FileDocument.class );
//                return files;
//
//        }
//
//
//}
