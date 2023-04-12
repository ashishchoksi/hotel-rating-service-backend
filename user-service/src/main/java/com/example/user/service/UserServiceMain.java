package com.example.user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceMain.class, args);
    }
}