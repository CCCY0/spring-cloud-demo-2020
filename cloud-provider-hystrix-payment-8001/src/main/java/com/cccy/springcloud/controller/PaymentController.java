package com.cccy.springcloud.controller;

import cn.hutool.json.JSONUtil;
import com.cccy.springcloud.entity.CommonResult;
import com.cccy.springcloud.entity.Payment;
import com.cccy.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Zhai
 * 2020/5/20 22:42
 */
@Slf4j
@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/payments/{id}", method = RequestMethod.GET)
    public CommonResult<Payment> getOk(@PathVariable Long id) {
        Payment payment = paymentService.getById(id);
        log.info("*****查询结果: {}", payment);
        if (payment != null) {
            return new CommonResult<>(200, "查询成功,服务端口号" + serverPort, payment);
        } else {
            return new CommonResult<>(400, "查询失败");
        }
    }

    @RequestMapping(value = "/payments/{id}/error", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "errorHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public CommonResult<Payment> getError(@PathVariable Long id) {
        Payment payment = paymentService.getById(id);
        try {
            // 手动超时3秒钟
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("*****查询结果: {}", payment);
        if (payment != null) {
            return new CommonResult<>(200, "查询成功,服务端口号" + serverPort, payment);
        } else {
            return new CommonResult<>(400, "查询失败");
        }
    }

    public CommonResult<Payment> errorHandler(Long id) {
        return new CommonResult<>(500, "调用异常, 触发服务降级,服务端口号" + serverPort);
    }

    /**
     * 以下是服务熔断
     */
    @RequestMapping(value = "/payments/{id}/circuitBreaker", method = RequestMethod.GET)
    public CommonResult<Payment> getCircuitBreaker(@PathVariable Long id) {
        Payment payment = paymentService.paymentCircuitBreaker(id);
        log.info("*****查询结果: {}", payment);
        if (payment != null) {
            return new CommonResult<>(200, "查询成功,服务端口号" + serverPort, payment);
        } else {
            return new CommonResult<>(400, "查询失败");
        }
    }
}
