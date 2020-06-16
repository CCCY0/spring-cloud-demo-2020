package com.cccy.springcloud.service;

import com.cccy.springcloud.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Zhai
 * 2020/6/16 10:39
 */
public interface StorageService {

    void decrease(Long productId, Integer count);
}
