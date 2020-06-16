package com.cccy.springcloud.service;

import com.cccy.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author Zhai
 * 2020/5/20 22:39
 */

public interface PaymentService {
    int create(Payment payment);

    Payment getById(Long id);

    Payment paymentCircuitBreaker(Long id);

    Payment circuitBreakerHandler(Long id);
}
