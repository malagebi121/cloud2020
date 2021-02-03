package com.embrace.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Author embrace
 * @Description
 * @Date create in  2020/11/27 21:01
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private  String  serverPort;

    @GetMapping(value = "/payment/zk")
    public String getServer(){
        log.info("=====  zk ");
        return  "spring with zookeeper : " + serverPort + "\t" + UUID.randomUUID().toString();
    }
}
