package com.example.demo.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * Created by fb on 2020/7/14
 */

@Setter
@Getter
@ToString
@Document
public class FileDocument {
        @Id  // 主键
        private String id;
        private String name;        // 文件名称
        private long size;          // 文件大小
        private Date uploadDate;    // 上传时间
        private String md5;         // 文件MD5值
        private byte[] content;     // 文件内容
        private String contentType; // 文件类型
        private String suffix;      // 文件后缀名
        private String description; // 文件描述
        private String gridfsId;    // 大文件管理GridFS的ID


        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof FileDocument)) return false;
                FileDocument that = (FileDocument) o;
                return getSize() == that.getSize() &&
                        Objects.equals(getId(), that.getId()) &&
                        Objects.equals(getName(), that.getName()) &&
                        Objects.equals(getUploadDate(), that.getUploadDate()) &&
                        Objects.equals(getMd5(), that.getMd5()) &&
                        Arrays.equals(getContent(), that.getContent()) &&
                        Objects.equals(getContentType(), that.getContentType()) &&
                        Objects.equals(getSuffix(), that.getSuffix()) &&
                        Objects.equals(getDescription(), that.getDescription()) &&
                        Objects.equals(getGridfsId(), that.getGridfsId());
        }

        @Override
        public int hashCode() {
                int result = Objects.hash(getId(), getName(), getSize(), getUploadDate(), getMd5(), getContentType(), getSuffix(), getDescription(), getGridfsId());
                result = 31 * result + Arrays.hashCode(getContent());
                return result;
        }
}
