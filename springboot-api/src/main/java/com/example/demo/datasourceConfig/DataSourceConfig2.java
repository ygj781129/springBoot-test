package com.example.demo.datasourceConfig;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by fb on 2020/6/4
 */
@Configuration
@MapperScan(basePackages = "com.example.demo.mapper.test02", sqlSessionFactoryRef = "test2SqlSessionFactory")
public class DataSourceConfig2 {
       /* @Bean(name = "test2DataSource")
        @ConfigurationProperties(prefix = "spring.datasource.test2")
        public DataSource getDateSource2() {
                return DataSourceBuilder.create().build();
        }*/
        @Bean(name = "test2SqlSessionFactory")
        public SqlSessionFactory test2SqlSessionFactory(@Qualifier("test2DataSource") DataSource datasource)
                throws Exception {
                SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
                bean.setDataSource(datasource);
                bean.setMapperLocations(
                        new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/test02/*.xml"));
                return bean.getObject();
        }
        @Bean("test2SqlSessionTemplate")
        public SqlSessionTemplate test2sqlsessiontemplate(
                @Qualifier("test2SqlSessionFactory") SqlSessionFactory sessionfactory) {
                return new SqlSessionTemplate(sessionfactory);
        }

/*
        @Bean(name = "test2TransactionManager")
        public DataSourceTransactionManager testTransactionManager(@Qualifier("test2DataSource") DataSource dataSource) {
                return new DataSourceTransactionManager(dataSource);
        }
*/


        @Bean("test2DataSource")
        public DataSource test2DataSource(Test2Config test2Config) throws SQLException {
                System.out.println(test2Config);
                MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
                mysqlXaDataSource.setUrl(test2Config.getUrl());
                mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
                mysqlXaDataSource.setPassword(test2Config.getPassword());
                mysqlXaDataSource.setUser(test2Config.getUsername());
                mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

                //注册到全局事务
                AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
                xaDataSource.setXaDataSource(mysqlXaDataSource);
                xaDataSource.setUniqueResourceName(test2Config.getUniqueResourceName());
                xaDataSource.setMinPoolSize(test2Config.getMinPoolSize());
                xaDataSource.setMaxPoolSize(test2Config.getMaxPoolSize());
                xaDataSource.setMaxLifetime(test2Config.getMaxLifetime());
                xaDataSource.setBorrowConnectionTimeout(test2Config.getBorrowConnectionTimeout());
                xaDataSource.setLoginTimeout(test2Config.getLoginTimeout());
                xaDataSource.setMaintenanceInterval(test2Config.getMaintenanceInterval());
                xaDataSource.setMaxIdleTime(test2Config.getMaxIdleTime());
                xaDataSource.setTestQuery(test2Config.getTestQuery());
                return xaDataSource;
        }
}
