package com.cccy.springcloud.controller;

import com.cccy.springcloud.entity.CommonResult;
import com.cccy.springcloud.service.StorageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Zhai
 * 2020/6/16 10:57
 */

@RestController
public class StorageController {
    @Resource
    private StorageService storageService;

    @RequestMapping(value = "storage/decrease", method = RequestMethod.POST)
    public CommonResult decrease(@RequestParam("productId") Long productId,
                                 @RequestParam("count") Integer count) {
        storageService.decrease(productId, count);
        return new CommonResult(200, "扣减库存成功");
    }
}
