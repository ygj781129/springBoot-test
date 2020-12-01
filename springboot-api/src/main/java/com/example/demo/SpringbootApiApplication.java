package com.example.demo;

import com.example.demo.datasourceConfig.Test1Config;
import com.example.demo.datasourceConfig.Test2Config;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableCaching//Redis注解的方式
@MapperScan("com.example.demo.mapper")//扫描的mapper
@Configuration
@EnableAsync
//@EnableTransactionManagement
@EnableConfigurationProperties({Test1Config.class, Test2Config.class})
public class SpringbootApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApiApplication.class, args);
	}
//	@Bean
//	public MultipartConfigElement multipartConfigElement() {
//		MultipartConfigFactory factory = new MultipartConfigFactory();
//		//单个文件最大
//		factory.setMaxFileSize("1024000KB"); //KB,MB
//		/// 设置总上传数据总大小
//		factory.setMaxRequestSize("10240000KB");
//		return factory.createMultipartConfig();
//	}

	/**
	 * TomcatServletWebServerFactory()
	 * ⽅法主要是为了解决上传文件较大时出现连接重置的问题，这个异常后台是捕捉不到的
	 * @return
	 */
	@Bean
	public TomcatServletWebServerFactory tomcatEmbedded() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
			if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<
							?>)) {
				//-1 means unlimited
				((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
			}
		});
		return tomcat;
	}
}
