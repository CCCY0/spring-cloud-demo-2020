package com.cccy.springcloud.service;

import com.cccy.springcloud.entity.CommonResult;
import com.cccy.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Zhai
 * 2020/6/14 19:18
 */

@FeignClient(value = "cloud-alibaba-payment-sentinel-provider", fallback = PaymentFeignFallBackService.class)
public interface PaymentService {

    @RequestMapping(value = "/payments/{id}", method = RequestMethod.GET)
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);
}
