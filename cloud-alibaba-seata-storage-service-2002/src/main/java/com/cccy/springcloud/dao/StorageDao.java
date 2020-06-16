package com.cccy.springcloud.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @author Zhai
 * 2020/5/20 22:25
 */

public interface StorageDao {

    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}
