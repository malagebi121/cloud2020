package com.embrace.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author embrace
 * @Description
 * @Date create in  2020/11/29 11:38
 */
@Component   // 这里标注了service 直接那边有延时直接就走异常处理了，不知道为什么
//@FeignClient(name = "CLOUD-PROVIDER-HYSTRIX-PAYMENT")
@FeignClient(name = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)  //service同一的service降级
public interface PaymentHystrixService {

    @GetMapping("/payment/hystrix/ok/{id}")
    String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    String paymentInfo_TimeOut(@PathVariable("id") Integer id);



}
