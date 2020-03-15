package org.zju.adm.api.config;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * ClassName: APIDocuments
 * Description: TODO
 * Created by tiamo on 15/3/2020 11:12 上午
 */
public class APIDocuments {

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
                .title("异常检测系统给")
                .contact(new Contact("ssdcxy", "https://www.ssdcxy.cn", "ssdcxy@gmail.com"))
                .description("API接口文档")
                .version("1.0.0")
                .termsOfServiceUrl("https://www.ssdcxy.cn")
                .build();
    }
}
