package com.cccy.springcloud.controller;

import com.cccy.springcloud.config.MyLB;
import com.cccy.springcloud.entity.CommonResult;
import com.cccy.springcloud.entity.Payment;
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
    public static final String PAYMENT_URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private MyLB myLB;

    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * 使用手写的负载均衡算法
     */
    @RequestMapping(value = "/consumer/payments/{id}", method = RequestMethod.GET)
    public CommonResult<Payment> get(@PathVariable Long id) {
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        ServiceInstance serviceInstance = myLB.instances(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "/payments/" + id, CommonResult.class);
    }

    @RequestMapping(value = "/consumer/payments", method = RequestMethod.POST)
    public CommonResult<Object> create(@RequestBody Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payments", payment, CommonResult.class);
    }
}
