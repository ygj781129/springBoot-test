package com.example.springbatch.config;

import com.example.springbatch.pojo.Person;
import com.example.springbatch.service.CvsItemProcessor;
import com.example.springbatch.util.CsvBeanValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 *
 * @author jian
 * @date 2019/4/28
 * @description spring batch cvs文件批处理配置需要注入Spring Batch以下组成部分
 * spring batch组成：
 * 1）JobRepository 注册job的容器
 * 2）JonLauncher 用来启动job的接口
 * 3）Job 实际执行的任务，包含一个或多个Step
 * 4）Step Step步骤包括ItemReader、ItemProcessor和ItemWrite
 * 5）ItemReader 读取数据的接口
 * 6）ItemProcessor 处理数据的接口
 * 7）ItemWrite 输出数据的接口
 *
 *
 */
@Configuration
@EnableBatchProcessing // 开启批处理的支持
@Import(DruidDBConfig.class) // 注入datasource
public class CsvBatchConfig {
        private Logger logger = LoggerFactory.getLogger(CsvBatchConfig.class);


        /**
         * ItemReader定义：读取文件数据+entirty映射
         * 用来读取数据，做实体类与数据字段之间的映射。比如读取csv文件中的人员数据，之后对应实体person的字段做mapper
         * @return
         */
        @Bean
        public ItemReader<Person> reader(){
                // 使用FlatFileItemReader去读cvs文件，一行即一条数据
                FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
                // 设置文件处在路径
                reader.setResource(new ClassPathResource("person.csv"));
                // entity与csv数据做映射
                reader.setLineMapper(new DefaultLineMapper<Person>() {
                        {
                                setLineTokenizer(new DelimitedLineTokenizer() {
                                        {
                                                setNames(new String[]{"id", "name", "age", "gender"});
                                        }
                                });
                                setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {
                                        {
                                                setTargetType(Person.class);
                                        }
                                });
                        }
                });
                return reader;
        }


        /**
         * 注册ItemProcessor: 处理数据+校验数据
         * 用来处理数据的接口，同时可以做数据校验（设置校验器，使用JSR-303(hibernate-validator)注解），
         * 比如将中文性别男/女，转为M/F。同时校验年龄字段是否符合要求等
         * @return
         */
        @Bean
        public ItemProcessor<Person, Person> processor(){
                CvsItemProcessor cvsItemProcessor = new CvsItemProcessor();
                // 设置校验器
                cvsItemProcessor.setValidator(csvBeanValidator());
                return cvsItemProcessor;
        }

        /**
         * 注册校验器
         * @return
         */
        @Bean
        public CsvBeanValidator csvBeanValidator(){
                return new CsvBeanValidator<Person>();
        }

        /**
         * ItemWriter定义：指定datasource，设置批量插入sql语句，写入数据库
         * 用来输出数据的接口，设置数据库源。编写预处理SQL插入语句
         * @param dataSource
         * @return
         */
        @Bean
        public ItemWriter<Person> writer(DataSource dataSource){
                // 使用jdbcBcatchItemWrite写数据到数据库中
                JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<>();
                // 设置有参数的sql语句
                writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
                String sql = "insert into person values(:id,:name,:age,:gender)";
                writer.setSql(sql);
                writer.setDataSource(dataSource);
                return writer;
        }

        /**
         * JobRepository定义：设置数据库，注册Job容器
         * @param dataSource
         * @param transactionManager
         * @return
         * @throws Exception
         */
        @Bean
        public JobRepository cvsJobRepository(DataSource dataSource, PlatformTransactionManager transactionManager) throws Exception{
                JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
                jobRepositoryFactoryBean.setDatabaseType("mysql");
                jobRepositoryFactoryBean.setTransactionManager(transactionManager);
                jobRepositoryFactoryBean.setDataSource(dataSource);
                return jobRepositoryFactoryBean.getObject();
        }

        /**
         * jobLauncher定义：
         * @param dataSource
         * @param transactionManager
         * @return
         * @throws Exception
         */
        @Bean
        public SimpleJobLauncher csvJobLauncher(DataSource dataSource, PlatformTransactionManager transactionManager) throws Exception{
                SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
                // 设置jobRepository
                jobLauncher.setJobRepository(cvsJobRepository(dataSource, transactionManager));
                return jobLauncher;
        }

        /**
         * 定义job
         * @param jobs
         * @param step
         * @return
         */
        @Bean
        public Job importJob(JobBuilderFactory jobs, Step step){
                return jobs.get("importCsvJob")
                        .incrementer(new RunIdIncrementer())
                        .flow(step)
                        .end()
                        .listener(csvJobListener())
                        .build();
        }

        /**
         * 注册job监听器
         * @return
         */
        @Bean
        public CsvJobListener csvJobListener(){
                return new CsvJobListener();
        }


        /**
         * step定义：步骤包括ItemReader->ItemProcessor->ItemWriter 即读取数据->处理校验数据->写入数据
         * @param stepBuilderFactory
         * @param reader
         * @param writer
         * @param processor
         * @return
         */
        @Bean
        public Step step(StepBuilderFactory stepBuilderFactory, ItemReader<Person> reader,
                         ItemWriter<Person> writer, ItemProcessor<Person, Person> processor){
                return stepBuilderFactory
                        .get("step")
                        .<Person, Person>chunk(65000) // Chunk的机制(即每次读取一条数据，再处理一条数据，累积到一定数量后再一次性交给writer进行写入操作)
                        .reader(reader)
                        .processor(processor)
                        .writer(writer)
                        .build();

        }
}