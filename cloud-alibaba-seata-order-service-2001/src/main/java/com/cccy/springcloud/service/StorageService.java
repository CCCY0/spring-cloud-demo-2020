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

@FeignClient(value = "cloud-alibaba-seata-storage-service")
public interface StorageService {

    @RequestMapping(value = "/storage/decrease", method = RequestMethod.POST)
    CommonResult decrease(@RequestParam("productId") Long productId,
                          @RequestParam("count") Integer count);
}
