package com.cccy.springcloud.dao;

import com.cccy.springcloud.domain.Order;
import com.cccy.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author Zhai
 * 2020/5/20 22:25
 */

public interface OrderDao {

    int create(Order order);

    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
