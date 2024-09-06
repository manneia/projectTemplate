package com.manneia.template;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author lkx
 */
@SpringBootApplication
@EnableScheduling
@MapperScan("com.manneia.template.mapper")
public class ProjectTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectTemplateApplication.class, args);
    }

}
