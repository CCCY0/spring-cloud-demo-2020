package com.cccy.springcloud.service;

import java.math.BigDecimal;

/**
 * @author Zhai
 * 2020/6/16 10:39
 */
public interface AccountService {

    void decrease(Long userId, BigDecimal money);
}
