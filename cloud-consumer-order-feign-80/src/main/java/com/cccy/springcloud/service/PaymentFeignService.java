package com.cccy.springcloud.service;

import com.cccy.springcloud.entity.CommonResult;
import com.cccy.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Zhai
 * 2020/5/23 0:45
 */
@Service
@FeignClient("cloud-payment-service")
public interface PaymentFeignService {
    @RequestMapping(value = "/payments/{id}", method = RequestMethod.GET)
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);
}
