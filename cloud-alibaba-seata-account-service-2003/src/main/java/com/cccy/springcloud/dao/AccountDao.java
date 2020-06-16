package com.cccy.springcloud.dao;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author Zhai
 * 2020/5/20 22:25
 */

public interface AccountDao {

    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
