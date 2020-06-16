package com.cccy.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zhai
 * 2020/6/4 00:25
 */

@RestController
@RefreshScope // 自动更新
public class ConfigController {
    @Value("${config.info}")
    private String configInfo;

    @RequestMapping(value = "/config", method = RequestMethod.GET)
    public ResponseEntity<Object> get() {
        return ResponseEntity.ok(configInfo);
    }
}
