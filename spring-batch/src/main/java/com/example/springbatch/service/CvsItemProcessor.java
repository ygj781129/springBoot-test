package com.example.springbatch.service;

/**
 * Created by fb on 2021/7/15
 */

import com.example.springbatch.pojo.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

/**
 * @author jian
 * @date 2019/4/28
 * @description
 * CSV文件数据处理及校验
 * 只需要实现ItemProcessor接口，重写process方法，输入的参数是从ItemReader读取到的数据，返回的数据给ItemWriter
 */
public class CvsItemProcessor extends ValidatingItemProcessor<Person> {
        private Logger logger = LoggerFactory.getLogger(CvsItemProcessor.class);

        @Override
        public Person process(Person item) throws ValidationException {
                // 执行super.process()才能调用自定义的校验器
                logger.info("processor start validating...");
                super.process(item);

                // 数据处理，比如将中文性别设置为M/F
                if ("男".equals(item.getGender())) {
                        item.setGender("M");
                } else {
                        item.setGender("F");
                }
                logger.info("processor end validating...");
                return item;
        }
}