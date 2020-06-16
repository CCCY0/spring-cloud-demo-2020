package com.cccy.springcloud;

import com.cccy.rule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author Zhai
 * 2020/5/20 23:27
 */
@EnableEurekaClient
// 使用别的负载均衡的设置
//@RibbonClient(name = "cloud-payment-service", configuration = MySelfRule.class)
@SpringBootApplication
public class OrderApplication80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication80.class, args);
    }

    @Bean
//    @LoadBalanced 使用手写的负载均衡算法, 所以需要注释掉
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
