package com.cccy.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cccy.springcloud.entity.CommonResult;
import com.cccy.springcloud.entity.Payment;
import com.cccy.springcloud.service.PaymentService;
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
public class FeignOrderController {
    @Resource
    private PaymentService paymentService;

    /**
     * SentinelResource没有任何配置时, 只会让Sentinel检测到fallback这个资源并在面板上展示出来
     * fallBack管理运行异常, blockHandler管理Sentinel配置违规
     */
    @RequestMapping(value = "/consumer/payments/{id}/feign", method = RequestMethod.GET)
    @SentinelResource(value = "fallback", blockHandler = "blockHandler", fallback = "handlerFallBack")
    public CommonResult<Payment> get(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }
}
