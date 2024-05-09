package org.com.bmw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Configuration
@MapperScan("org.com.bmw.dao")
public class AppMain {
    public static void main(String[] args){
        SpringApplication.run(AppMain.class, args);
    }
}
