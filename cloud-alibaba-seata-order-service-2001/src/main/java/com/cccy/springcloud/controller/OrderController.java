package com.cccy.springcloud.controller;

import com.cccy.springcloud.domain.Order;
import com.cccy.springcloud.entity.CommonResult;
import com.cccy.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Zhai
 * 2020/6/16 10:57
 */

@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @RequestMapping(value = "order/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody Order order) {
        orderService.create(order);
        return new CommonResult(200, "订单创建成功");
    }
}
