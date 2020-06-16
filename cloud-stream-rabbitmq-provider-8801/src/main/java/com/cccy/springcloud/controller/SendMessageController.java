package com.cccy.springcloud.controller;

import com.cccy.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Zhai
 * 2020/6/3 1:17
 */

@RestController
public class SendMessageController {
    @Resource
    private IMessageProvider messageProvider;

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public String sendMessage() {
        return messageProvider.send();
    }
}
