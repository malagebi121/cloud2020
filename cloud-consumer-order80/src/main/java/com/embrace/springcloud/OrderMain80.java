package com.embrace.springcloud;

import com.embrace.ribbon.RibbonRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @Author embrace
 * @Description
 * @Date create in  2020/11/26 16:11
 */
@SpringBootApplication
@EnableEurekaClient
//ribbon 轮询配置，不能和主启动在一个包下
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = RibbonRuleConfig.class)
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
