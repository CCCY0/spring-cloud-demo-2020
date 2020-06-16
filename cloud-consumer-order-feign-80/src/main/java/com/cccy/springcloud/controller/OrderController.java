package com.cccy.springcloud.controller;

import com.cccy.springcloud.entity.CommonResult;
import com.cccy.springcloud.entity.Payment;
import com.cccy.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Zhai
 * 2020/5/20 23:29
 */
@RestController
public class OrderController {
    @Resource
    private PaymentFeignService paymentFeignService;

    /**
     * 使用Feign调用接口
     */
    @RequestMapping(value = "/consumer/payments/{id}", method = RequestMethod.GET)
    public CommonResult<Payment> get(@PathVariable Long id) {
        return paymentFeignService.getPaymentById(id);
    }
}
