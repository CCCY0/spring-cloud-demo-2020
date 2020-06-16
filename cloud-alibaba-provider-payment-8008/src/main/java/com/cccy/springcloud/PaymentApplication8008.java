package com.cccy.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Zhai
 * 2020/5/20 21:59
 */
@EnableDiscoveryClient
@MapperScan("com.cccy.springcloud.dao")
@SpringBootApplication
public class PaymentApplication8008 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication8008.class, args);
    }
}
