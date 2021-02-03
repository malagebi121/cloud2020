package com.embrace.springcloud.controller;

import com.embrace.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Yang Hao
 * @description
 * @date 2020-09-21 15:33
 */
@RestController
@Slf4j
public class SendMessageController {
    @Resource
    private IMessageProvider iMessageProvider;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/sendMessage")
    public String sendMessage() {
        String msg = iMessageProvider.sendMsg();
        log.info("生产者 ------> 生产消息：[{}]; port:[{}]", msg, serverPort);
        return msg;
    }

}
