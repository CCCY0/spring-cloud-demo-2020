package com.cccy.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cccy.springcloud.entity.CommonResult;
import com.cccy.springcloud.entity.Payment;

/**
 * @author Zhai
 * 2020/6/12 15:35
 */

public class CustomerBlockHandler {
    public static CommonResult<Payment> handlerExceptionOne(BlockException e) {
        return new CommonResult<>(501, "执行失败,触发自定义handler1");
    }

    public static CommonResult<Payment> handlerExceptionTwo(BlockException e) {
        return new CommonResult<>(501, "执行失败,触发自定义handler2");
    }
}
