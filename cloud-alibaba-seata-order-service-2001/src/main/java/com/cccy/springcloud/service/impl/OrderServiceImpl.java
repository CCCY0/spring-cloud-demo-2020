package com.cccy.springcloud.service.impl;

import com.cccy.springcloud.dao.OrderDao;
import com.cccy.springcloud.domain.Order;
import com.cccy.springcloud.service.AccountService;
import com.cccy.springcloud.service.OrderService;
import com.cccy.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Zhai
 * 2020/6/16 10:36
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    @GlobalTransactional(name = "create-order", rollbackFor = Exception.class)
    @Override
    public void create(Order order) {
        log.info("------->开始创建订单");
        orderDao.create(order);
        log.info("------->创建订单完成");

        log.info("------->开始调用库存服务,扣减库存");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("------->扣减库存完成");

        log.info("------->开始调用账户服务,扣减余额");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("------->扣减余额完成");

        log.info("------->修改订单状态开始");
        orderDao.update(order.getUserId(), 0);
        log.info("------->修改订单状态结束");
    }
}
