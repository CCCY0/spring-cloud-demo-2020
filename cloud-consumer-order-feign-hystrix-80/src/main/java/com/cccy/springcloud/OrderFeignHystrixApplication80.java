package com.cccy.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Zhai
 * 2020/5/20 23:27
 */
@EnableHystrix
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class OrderFeignHystrixApplication80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignHystrixApplication80.class, args);
    }
}
