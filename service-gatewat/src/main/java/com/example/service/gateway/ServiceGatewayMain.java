package com.example.service.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceGatewayMain {
    public static void main(String[] args) {
        SpringApplication.run(ServiceGatewayMain.class, args);
    }
}