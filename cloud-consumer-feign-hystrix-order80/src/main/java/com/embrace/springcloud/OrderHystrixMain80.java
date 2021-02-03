package com.embrace.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author embrace
 * @Description
 * @Date create in  2020/11/29 11:31
 */
@SpringBootApplication
@EnableFeignClients
@EnableHystrix    // 加入hystrix，一般降级在客户端处理
public class OrderHystrixMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderHystrixMain80.class, args);
    }
}
