package com.cccy.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Zhai
 * 2020/5/23 1:08
 */
@Configuration
public class FeignConfig {

    /**
     * 返回所有日志FULL
     * @return 日志级别
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
