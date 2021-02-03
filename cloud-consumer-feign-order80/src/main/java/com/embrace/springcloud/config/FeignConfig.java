package com.embrace.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author embrace
 * @Description   feign  日志分为四个等级
 * @Date create in  2020/11/28 21:15
 */
@Configuration
public class FeignConfig {

    @Bean
    Logger.Level  getFeignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
