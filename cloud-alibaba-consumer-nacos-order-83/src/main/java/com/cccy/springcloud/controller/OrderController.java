package com.cccy.springcloud.controller;

import com.cccy.springcloud.config.MyLB;
import com.cccy.springcloud.entity.CommonResult;
import com.cccy.springcloud.entity.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author Zhai
 * 2020/5/20 23:29
 */
@SuppressWarnings("unchecked")
@RestController
public class OrderController {
    @Value("${service-url.nacos-user-service}")
    public String paymentUrl;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private MyLB myLB;

    @RequestMapping(value = "/consumer/payments/{id}", method = RequestMethod.GET)
    public CommonResult<Payment> get(@PathVariable Long id) {
        return restTemplate.getForObject(paymentUrl + "/payments/" + id, CommonResult.class);
    }

    @RequestMapping(value = "/consumer/payments", method = RequestMethod.POST)
    public CommonResult<Object> create(@RequestBody Payment payment) {
        return restTemplate.postForObject(paymentUrl + "/payments", payment, CommonResult.class);
    }
}
