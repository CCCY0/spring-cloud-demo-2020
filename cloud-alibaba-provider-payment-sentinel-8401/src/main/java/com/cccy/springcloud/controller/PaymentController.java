package com.cccy.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cccy.springcloud.entity.CommonResult;
import com.cccy.springcloud.entity.Payment;
import com.cccy.springcloud.handler.CustomerBlockHandler;
import com.cccy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
            return new CommonResult<>(200, "数据库插入成功,服务端口号" + serverPort, result);
        } else {
            return new CommonResult<>(500, "数据库插入失败");
        }
    }

    /**
     * 一定会触发异常的接口, 用来测试服务降级与熔断
     */
    @RequestMapping(value = "/payments/{id}/error", method = RequestMethod.GET)
    public CommonResult<Payment> getAlwaysError(@PathVariable Long id) {
        int a = 1 / 0;
        Payment payment = paymentService.getById(id);
        log.info("*****查询结果: {}", payment);
        if (payment != null) {
            return new CommonResult<>(200, "查询成功,服务端口号" + serverPort, payment);
        } else {
            return new CommonResult<>(400, "查询失败");
        }
    }

    /**
     * 用来测试热点规则
     */
    @RequestMapping(value = "/payments/hotkeys", method = RequestMethod.GET)
    @SentinelResource(value = "payments/hotkeys", blockHandler = "handler")
    public CommonResult<Payment> hotKey(@RequestParam("p1") Long p1,
                                        @RequestParam("p2") Long p2) {
        log.info("*****执行结果: {}", "成功");
        return new CommonResult<>(200, "执行成功,服务端口号" + serverPort);
    }

    public CommonResult<Payment> handler(Long p1, Long p2, BlockException exception) {
        exception.printStackTrace();
        return new CommonResult<>(501, "触发热点规则限流~, 端口号" + serverPort);
    }

    /**
     * 用来测试自定义handler类
     */
    @RequestMapping(value = "/payments/customClass", method = RequestMethod.GET)
    @SentinelResource(value = "payments/customClass",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerExceptionOne")
    public CommonResult<Payment> custom() {
        log.info("*****执行结果: {}", "成功");
        return new CommonResult<>(200, "执行成功,服务端口号" + serverPort);
    }
}
