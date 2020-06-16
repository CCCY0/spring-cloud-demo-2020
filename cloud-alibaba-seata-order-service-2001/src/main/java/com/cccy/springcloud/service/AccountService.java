package com.cccy.springcloud.service;

import com.cccy.springcloud.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author Zhai
 * 2020/6/16 10:39
 */

@FeignClient(value = "cloud-alibaba-seata-account-service")
public interface AccountService {

    /**
     * 扣减余额
     * @param userId 用户ID
     * @param money 需要扣减的余额
     */
    @RequestMapping(value = "/account/decrease", method = RequestMethod.POST)
    CommonResult decrease(@RequestParam("userId") Long userId,
                          @RequestParam("money") BigDecimal money);
}
