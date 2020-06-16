package com.cccy.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Zhai
 * 2020/5/20 21:59
 */
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
public class GateWayApplication9527 {
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication9527.class, args);
    }
}
