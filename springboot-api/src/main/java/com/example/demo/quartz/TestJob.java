package com.example.demo.quartz;



import com.example.demo.util.DateUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.util.Date;

/**
 * @program: xiudo-ota
 * @description: 测试定时任务
 * @author: zhang yi
 * @create: 2020-10-09 14:38
 */
@DisallowConcurrentExecution//Job中的任务有可能并发执行，例如任务的执行时间过长，而每次触发的时间间隔太短，则会导致任务会被并发执行。如果是并发执行，就需要一个数据库锁去避免一个数据被多次处理。
public class TestJob implements Job {
        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
                System.err.println(jobExecutionContext.getJobDetail().getJobDataMap().get("name"));
                System.err.println(jobExecutionContext.getJobDetail().getJobDataMap().get("age"));
                System.err.println(jobExecutionContext.getTrigger().getJobDataMap().get("orderNo"));
                System.err.println("定时任务执行，当前时间："+ DateUtils.formatDateTime(new Date()));
        }
}