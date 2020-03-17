package org.zju.adm.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * ClassName: APIDocuments
 * Description: api 接口文档
 * Created by tiamo on 15/3/2020 11:12 上午
 */
@EnableSwagger2
@Configuration
public class APIDocuments {

    // 访问地址 http://localhost:8088/doc.html
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.zju.adm.api.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("异常检测系统")
                .contact(new Contact("ssdcxy", "https://www.ssdcxy.cn", "ssdcxy@gmail.com"))
                .description("API接口文档")
                .version("1.0.0")
                .termsOfServiceUrl("https://www.ssdcxy.cn")
                .build();
    }
}
