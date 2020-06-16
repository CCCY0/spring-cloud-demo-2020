package com.cccy.springcloud.service.impl;

import com.cccy.springcloud.dao.PaymentDao;
import com.cccy.springcloud.entity.Payment;
import com.cccy.springcloud.service.PaymentService;
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
}
