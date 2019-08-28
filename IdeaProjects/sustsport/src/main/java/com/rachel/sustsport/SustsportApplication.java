package com.rachel.sustsport;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.rachel.sustsport.mapper")
@SpringBootApplication
public class SustsportApplication {

    public static void main(String[] args) {
        SpringApplication.run(SustsportApplication.class, args);
    }

}
