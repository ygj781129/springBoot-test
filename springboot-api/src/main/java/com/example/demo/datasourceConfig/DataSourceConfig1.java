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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by fb on 2020/6/4
 */

//表示这个类为一个配置类
@Configuration
// 配置mybatis的接口类放的地方
@MapperScan(basePackages = "com.example.demo.mapper.test01", sqlSessionFactoryRef = "test1SqlSessionFactory")
public class DataSourceConfig1 {
/*
        // 将这个对象放入Spring容器中
        @Bean(name = "test1DataSource")
        // 表示这个数据源是默认数据源
        @Primary
        // 读取application.properties中的配置参数映射成为一个对象
        // prefix表示参数的前缀
        @ConfigurationProperties(prefix = "spring.datasource.test1")
        public DataSource getDateSource1() {
                return DataSourceBuilder.create().build();
        }
        */
        @Bean(name = "test1SqlSessionFactory")
        // 表示这个数据源是默认数据源
        @Primary
        // @Qualifier表示查找Spring容器中名字为test1DataSource的对象
        public SqlSessionFactory test1SqlSessionFactory(@Qualifier("test1DataSource") DataSource datasource)
                throws Exception {
                SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
                bean.setDataSource(datasource);
                bean.setMapperLocations(
                        // 设置mybatis的xml所在位置
                        new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/test01/*.xml"));
                return bean.getObject();
        }
        @Bean("test1SqlSessionTemplate")
        // 表示这个数据源是默认数据源
        @Primary
        public SqlSessionTemplate test1sqlsessiontemplate(
                @Qualifier("test1SqlSessionFactory") SqlSessionFactory sessionfactory) {
                return new SqlSessionTemplate(sessionfactory);
        }

/*
        @Bean(name = "test1TransactionManager")
        @Primary
        public DataSourceTransactionManager testTransactionManager(@Qualifier("test1DataSource") DataSource dataSource) {
                return new DataSourceTransactionManager(dataSource);
        }*/


        @Bean("test1DataSource")
        public DataSource test1DataSource(Test1Config test1Config) throws SQLException {
                System.out.println(test1Config);
                MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
                mysqlXaDataSource.setUrl(test1Config.getUrl());
                mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
                mysqlXaDataSource.setPassword(test1Config.getPassword());
                mysqlXaDataSource.setUser(test1Config.getUsername());
                mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
                //注册到全局事务
                AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
                xaDataSource.setXaDataSource(mysqlXaDataSource);
                xaDataSource.setUniqueResourceName(test1Config.getUniqueResourceName());
                xaDataSource.setMinPoolSize(test1Config.getMinPoolSize());
                xaDataSource.setMaxPoolSize(test1Config.getMaxPoolSize());
                xaDataSource.setMaxLifetime(test1Config.getMaxLifetime());
                xaDataSource.setBorrowConnectionTimeout(test1Config.getBorrowConnectionTimeout());
                xaDataSource.setLoginTimeout(test1Config.getLoginTimeout());
                xaDataSource.setMaintenanceInterval(test1Config.getMaintenanceInterval());
                xaDataSource.setMaxIdleTime(test1Config.getMaxIdleTime());
                xaDataSource.setTestQuery(test1Config.getTestQuery());
                return xaDataSource;
        }

}


