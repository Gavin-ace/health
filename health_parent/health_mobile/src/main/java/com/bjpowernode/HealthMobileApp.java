package com.bjpowernode;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class HealthMobileApp {
    public static void main(String[] args) {
        SpringApplication.run(HealthMobileApp.class, args);
    }
}