package com.example.demo.mongo;

import com.example.demo.pojo.FileModel;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by fb on 2020/7/14
 */
public interface FileRepository extends MongoRepository<FileModel,String> {
}
