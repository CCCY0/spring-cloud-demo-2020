package com.cccy.springcloud.dao;

import com.cccy.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author Zhai
 * 2020/5/20 22:25
 */

public interface PaymentDao {

    int create(Payment payment);

    Payment getById(@Param("id") Long id);
}
