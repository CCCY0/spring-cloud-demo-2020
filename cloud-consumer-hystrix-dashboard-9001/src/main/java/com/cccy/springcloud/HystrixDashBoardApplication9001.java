package com.cccy.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author Zhai
 * 2020/5/20 23:27
 */
@EnableHystrixDashboard
@EnableEurekaClient
@SpringBootApplication
public class HystrixDashBoardApplication9001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashBoardApplication9001.class, args);
    }
}
