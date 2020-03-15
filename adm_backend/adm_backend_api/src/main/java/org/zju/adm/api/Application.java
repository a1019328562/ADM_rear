package org.zju.adm.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * ClassName: org.zju.adm.api.Application
 * Description: TODO
 * Created by tiamo on 15/3/2020 10:56 上午
 */
@SpringBootApplication(scanBasePackages = {"org.zju.adm"}, exclude = SecurityAutoConfiguration.class)
@MapperScan(basePackages = "org.zju.adm.mapper")
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}