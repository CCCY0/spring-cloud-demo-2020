package com.cccy.springcloud;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Zhai
 * 2020/5/20 21:59
 */
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.cccy.springcloud.dao")
@EnableAutoDataSourceProxy
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
public class AccountApplication2003 {
    public static void main(String[] args) {
        SpringApplication.run(AccountApplication2003.class, args);
    }
}
