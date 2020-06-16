package com.cccy.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.cccy.springcloud.dao.PaymentDao;
import com.cccy.springcloud.entity.Payment;
import com.cccy.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Zhai
 * 2020/5/20 22:40
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getById(Long id) {
        return paymentDao.getById(id);
    }

    // 服务熔断 10秒内请求次数10次如果达到60%的失败率则断路器开启
    @Override
    @HystrixCommand(fallbackMethod = "circuitBreakerHandler", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),// 失败率达到百分之多少后跳闸
    })
    public Payment paymentCircuitBreaker(Long id) {
        if (id < 0) {
            throw new RuntimeException("id不能为负数");
        }
        return getById(id);
    }

    @Override
    public Payment circuitBreakerHandler(Long id) {
        Payment payment = new Payment();
        payment.setSerial("触发服务降级或熔断, id为负数或为负数的次数过多");
        return payment;
    }
}
