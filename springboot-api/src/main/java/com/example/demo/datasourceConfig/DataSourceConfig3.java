package com.example.demo.datasourceConfig;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * Created by fb on 2020/6/4
 */
@Configuration
@MapperScan(basePackages = "com.example.demo.mapper.test03", sqlSessionFactoryRef = "test3SqlSessionFactory")
public class DataSourceConfig3 {
        @Bean(name = "test3DataSource")
        @ConfigurationProperties(prefix = "spring.datasource.test3")
        public DataSource getDateSource3() {
                return DataSourceBuilder.create().build();
        }
        @Bean(name = "test3SqlSessionFactory")
        public SqlSessionFactory test3SqlSessionFactory(@Qualifier("test3DataSource") DataSource datasource)
                throws Exception {
                SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
                bean.setDataSource(datasource);
                bean.setMapperLocations(
                        new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/test03/*.xml"));
                return bean.getObject();
        }
        @Bean("test3SqlSessionTemplate")
        public SqlSessionTemplate test3sqlsessiontemplate(
                @Qualifier("test3SqlSessionFactory") SqlSessionFactory sessionfactory) {
                return new SqlSessionTemplate(sessionfactory);
        }
}
