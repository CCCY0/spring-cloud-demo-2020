package com.cccy.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cccy.springcloud.config.MyLB;
import com.cccy.springcloud.entity.CommonResult;
import com.cccy.springcloud.entity.Payment;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${service-url.nacos-user-service}")
    public String paymentUrl;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private MyLB myLB;

    /**
     * SentinelResource没有任何配置时, 只会让Sentinel检测到fallback这个资源并在面板上展示出来
     * fallBack管理运行异常, blockHandler管理Sentinel配置违规
     */
    @RequestMapping(value = "/consumer/payments/{id}", method = RequestMethod.GET)
    @SentinelResource(value = "fallback", blockHandler = "blockHandler", fallback = "handlerFallBack")
    public CommonResult<Payment> get(@PathVariable Long id) {
        if (id.equals(1L)) {
            throw new IllegalArgumentException("内部异常(模拟服务器抛异常)");
        }
        return restTemplate.getForObject(paymentUrl + "/payments/" + id, CommonResult.class);
    }

    /**
     * 自定义限流必须要有 BlockException 参数传入
     */
    public CommonResult<Payment> blockHandler(Long id, BlockException e) {
        e.printStackTrace();
        return new CommonResult<>(444, "Sentinel限流");
    }

    public CommonResult<Payment> handlerFallBack(Long id, Throwable e) {
        e.printStackTrace();
        return new CommonResult<>(501, "服务器开小差了(异常)");
    }

    @RequestMapping(value = "/consumer/payments", method = RequestMethod.POST)
    public CommonResult<Object> create(@RequestBody Payment payment) {
        return restTemplate.postForObject(paymentUrl + "/payments", payment, CommonResult.class);
    }
}
