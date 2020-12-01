package com.example.demo.util;

import com.example.demo.enums.FileEnums;
import com.example.demo.vo.ReturnVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * Created by fb on 2020/9/17
 */
@Slf4j
public class FileValidUtil {

        public static ReturnVo CheckFile(MultipartFile file,Long size)throws Exception{

                //检查文件是否为空
                if(file.isEmpty()) {
                        return  ReturnVo.build(FileEnums.FILE_IS_EMPTY.getCode(),FileEnums.FILE_IS_EMPTY.getMessage());
                }
                //检查文件大小
                if(file.getSize() > size) {
                        return  ReturnVo.build(FileEnums.FILE_SIZE_ERROR.getCode(),
                                FileEnums.FILE_SIZE_ERROR.getMessage()+"问价大小不能超过"+size);
                }
                //检查是否是图片
                BufferedImage bi = ImageIO.read(file.getInputStream());
                if(bi == null){
                        return  ReturnVo.build(FileEnums.FILE_TYPE_ERROR.getCode(),FileEnums.FILE_TYPE_ERROR.getMessage());
                }
                log.info(FileEnums.FILE_TYPE_ERROR.getMessage());
                return ReturnVo.build(FileEnums.FILE_CHECK_PASS.getCode(),FileEnums.FILE_CHECK_PASS.getMessage());

        }
}
