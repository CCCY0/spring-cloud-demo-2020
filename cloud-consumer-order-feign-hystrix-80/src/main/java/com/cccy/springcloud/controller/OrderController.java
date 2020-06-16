package com.cccy.springcloud.controller;

import com.cccy.springcloud.entity.CommonResult;
import com.cccy.springcloud.entity.Payment;
import com.cccy.springcloud.service.PaymentFeignFallBackService;
import com.cccy.springcloud.service.PaymentFeignService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
@DefaultProperties(defaultFallback = "defaultErrorHandler")
public class OrderController {
    @Resource
    private PaymentFeignService paymentFeignService;

    /**
     * 使用Feign调用接口 正常
     */
    @RequestMapping(value = "/consumer/payments/{id}", method = RequestMethod.GET)
    public CommonResult<Payment> get(@PathVariable Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    /**
     * 使用Feign调用接口 延时三秒的
     */
    @RequestMapping(value = "/consumer/payments/{id}/error", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "errorHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })
    public CommonResult<Payment> getError(@PathVariable Long id) {
        return paymentFeignService.getError(id);
    }

    /**
     * 使用Feign调用接口 延时三秒的 使用默认的降级策略
     */
    @RequestMapping(value = "/consumer/payments/{id}/defaultError", method = RequestMethod.GET)
    @HystrixCommand
    public CommonResult<Payment> getDefaultError(@PathVariable Long id) {
        return paymentFeignService.getError(id);
    }

    /**
     * 使用Feign调用接口 延时三秒的 使用{@link PaymentFeignFallBackService}的降级策略
     */
    @RequestMapping(value = "/consumer/payments/{id}/fullBackError", method = RequestMethod.GET)
    public CommonResult<Payment> getFullBackError(@PathVariable Long id) {
        return paymentFeignService.getError(id);
    }

    public CommonResult<Payment> errorHandler(Long id) {
        return new CommonResult<>(500, "调用异常, 触发消费端服务降级");
    }

    public CommonResult<Payment> defaultErrorHandler() {
        return new CommonResult<>(500, "调用异常, 触发消费端默认的服务降级");
    }
}
