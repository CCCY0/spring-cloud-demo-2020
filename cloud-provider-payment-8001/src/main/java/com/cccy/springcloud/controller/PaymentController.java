package com.cccy.springcloud.controller;

import cn.hutool.json.JSONUtil;
import com.cccy.springcloud.entity.CommonResult;
import com.cccy.springcloud.entity.Payment;
import com.cccy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    public CommonResult<Payment> get(@PathVariable Long id) {
        Payment payment = paymentService.getById(id);
        log.info("*****查询结果: {}", payment);
        if (payment != null) {
            return new CommonResult<>(200, "查询成功,服务端口号" + serverPort, payment);
        } else {
            return new CommonResult<>(400, "查询失败");
        }
    }

    @RequestMapping(value = "/payments", method = RequestMethod.POST)
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("*****插入结果: {}", result);
        if (result > 0) {
            return new CommonResult<>(200, "数据库插入成功, 服务端口号" + serverPort, result);
        } else {
            return new CommonResult<>(500, "数据库插入失败");
        }
    }

    @RequestMapping(value = "/discovery", method = RequestMethod.GET)
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        services.forEach((i) ->log.info("*****element: {}", i));
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        instances.forEach((i) -> log.info("*****instance: {},{},{},{}", i.getInstanceId(), i.getServiceId(), i.getUri(), i.getHost()));
        return this.discoveryClient;
    }
}
