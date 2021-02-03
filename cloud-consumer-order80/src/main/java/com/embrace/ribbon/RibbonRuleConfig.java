package com.embrace.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author embrace
 * @Description
 * @Date create in  2020/11/27 23:19
 */
@Configuration
public class RibbonRuleConfig {

    //  ribbon  有其中轮询策略，这里只是指定为随机策略，默认为轮询策略
    @Bean
    public IRule getRibbonRule(){
        return  new RandomRule();
    }
}
