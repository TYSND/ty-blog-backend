package com.ty_home_backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.ty_home_backend.dao")
@SpringBootApplication
public class TyHomeBackEndApplication {
    public static void main (String[] args) {
        System.out.println(TyHomeBackEndApplication.class);
        SpringApplication.run(TyHomeBackEndApplication.class, args);
    }
}
