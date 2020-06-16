package com.cccy.springcloud.controller;

import com.cccy.springcloud.entity.CommonResult;
import com.cccy.springcloud.entity.Payment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

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

    @RequestMapping(value = "/consumer/payments/{id}", method = RequestMethod.GET)
    public CommonResult<Payment> get(@PathVariable Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payments/" + id, CommonResult.class);
    }

    @RequestMapping(value = "/consumer/payments", method = RequestMethod.POST)
    public CommonResult<Object> create(@RequestBody Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payments", payment, CommonResult.class);
    }
}
