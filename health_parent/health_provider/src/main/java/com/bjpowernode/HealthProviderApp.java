package com.bjpowernode;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@MapperScan(basePackages = "com.bjpowernode.mapper")
public class HealthProviderApp {
    public static void main(String[] args) {
        SpringApplication.run(HealthProviderApp.class,args);
    }
}
