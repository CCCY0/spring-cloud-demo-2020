package com.cccy.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.internal.EnableZipkinServer;

/**
 * @author Zhai
 * 2020/5/20 21:59
 */
@EnableZipkinServer
@SpringBootApplication
public class ZipkinServerApplication7777 {
    public static void main(String[] args) {
        SpringApplication.run(ZipkinServerApplication7777.class, args);
    }
}
