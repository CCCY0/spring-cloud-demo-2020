package com.cccy.springcloud.service.impl;

import com.cccy.springcloud.dao.AccountDao;
import com.cccy.springcloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author Zhai
 * 2020/6/16 15:54
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info("------>account-service扣减账户余额开始");
        // todo: 模拟超时异常, 分布式事物回滚
//        try {
//            TimeUnit.SECONDS.sleep(20);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        accountDao.decrease(userId, money);
        log.info("------>account-service扣减账户余额结束");
    }
}
