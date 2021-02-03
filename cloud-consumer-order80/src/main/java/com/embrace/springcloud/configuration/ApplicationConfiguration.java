package com.embrace.springcloud.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author embrace
 * @Description
 * @Date create in  2020/11/26 16:34
 */
@Configuration
public class ApplicationConfiguration {

    @Bean
//    @LoadBalanced  //负载均衡，多台机子不添加会报错
    public RestTemplate getRestTemplate(){
        return new  RestTemplate();
    }
}
