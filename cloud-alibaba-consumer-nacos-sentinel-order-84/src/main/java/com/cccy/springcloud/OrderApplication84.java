package com.cccy.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author Zhai
 * 2020/5/20 23:27
 */
@EnableDiscoveryClient
// 使用别的负载均衡的设置
//@RibbonClient(name = "cloud-payment-service", configuration = MySelfRule.class)
@EnableFeignClients
@SpringBootApplication
public class OrderApplication84 {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication84.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
