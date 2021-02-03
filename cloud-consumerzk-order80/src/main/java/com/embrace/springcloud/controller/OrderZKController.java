package com.embrace.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author embrace
 * @Description
 * @Date create in  2020/11/27 21:37
 */
@RestController
@Slf4j
public class OrderZKController {

    private static  final  String  URL = "http://cloud-provider-payment/";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value =  "/consumer")
    public String payment(){
        log.info("=== ZK消费端调用");
        return  restTemplate.getForObject(URL + "payment/zk",String.class);
    }
}
