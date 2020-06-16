package com.cccy.springcloud.controller;

import com.cccy.springcloud.entity.CommonResult;
import com.cccy.springcloud.service.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author Zhai
 * 2020/6/16 10:57
 */

@RestController
public class AccountController {
    @Resource
    private AccountService accountService;

    @RequestMapping(value = "account/decrease", method = RequestMethod.POST)
    public CommonResult decrease(@RequestParam Long userId,
                                 @RequestParam BigDecimal money) {
        accountService.decrease(userId, money);
        return new CommonResult(200, "扣减余额成功");
    }
}
