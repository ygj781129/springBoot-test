package com.example.springbatch.config;

/**
 * Created by fb on 2021/7/15
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * @author jian
 * @date 2019/4/28
 * @description
 * 监听Job执行情况，则定义一个类实现JobExecutorListener，并定义Job的Bean上绑定该监听器
 */
public class CsvJobListener implements JobExecutionListener {

        private Logger logger = LoggerFactory.getLogger(CsvJobListener.class);
        private long startTime;
        private long endTime;

        @Override
        public void beforeJob(JobExecution jobExecution) {
                startTime = System.currentTimeMillis();
                logger.info("job process start...");
        }

        @Override
        public void afterJob(JobExecution jobExecution) {
                endTime = System.currentTimeMillis();
                logger.info("job process end...");
                logger.info("elapsed time: " + (endTime - startTime) + "ms");
        }
}