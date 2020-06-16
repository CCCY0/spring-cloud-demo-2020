package com.cccy.springcloud.service.impl;

import com.cccy.springcloud.dao.StorageDao;
import com.cccy.springcloud.entity.CommonResult;
import com.cccy.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Zhai
 * 2020/6/16 15:38
 */
@Slf4j
@Service
public class StorageServiceImpl implements StorageService {
    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        log.info("------>storage-service开始扣减库存");
        storageDao.decrease(productId, count);
        log.info("------>storage-service扣减库存结束");
    }
}
