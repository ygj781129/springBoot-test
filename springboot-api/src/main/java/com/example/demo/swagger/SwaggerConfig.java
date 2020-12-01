package com.example.demo.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by fb on 2020/6/4
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

        /**
         * 初始化创建Swagger Api
         */
        @Bean
        public Docket createRestApi() {
                return new Docket(DocumentationType.SWAGGER_2)
                        // 详细信息定制
                        .apiInfo(apiInfo())
                        .select()
                        // 指定当前包路径
                        .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                        // 扫描所有 .apis(RequestHandlerSelectors.any())
                        .paths(PathSelectors.any())
                        .build();
        }

        /**
         * 添加摘要信息
         */
        private ApiInfo apiInfo() {
                // 用ApiInfoBuilder进行定制
                return new ApiInfoBuilder()
                        .title("标题：springBoot-Swagger2整合学习")
                        .description("描述：文档构建器")
                        .contact("fanbin")
                        .version("版本号: 1.0")
                        .build();
        }


}
