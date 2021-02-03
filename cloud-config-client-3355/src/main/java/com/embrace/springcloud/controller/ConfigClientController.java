package com.embrace.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  获取配置controller
 */
@RestController
@Slf4j
@RefreshScope   //  加上这个才能手动刷新
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")  //  这里是大写
    public String getConfigInfo() {
        return configInfo;
    }

}
