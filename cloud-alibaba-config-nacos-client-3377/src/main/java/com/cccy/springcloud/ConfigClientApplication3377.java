package com.cccy.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Zhai
 * 2020/6/4 00:25
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConfigClientApplication3377 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication3377.class, args);
    }
}
