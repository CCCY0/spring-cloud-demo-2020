package com.cccy.springcloud.service;

import com.cccy.springcloud.entity.CommonResult;
import com.cccy.springcloud.entity.Payment;
import org.springframework.stereotype.Service;

/**
 * @author Zhai
 * 2020/5/25 0:25
 */
@Service
public class PaymentFeignFallBackService implements PaymentService{
    @Override
    public CommonResult<Payment> getPaymentById(Long id) {
        return new CommonResult<>(500, "触发消费端fallback");
    }
}
