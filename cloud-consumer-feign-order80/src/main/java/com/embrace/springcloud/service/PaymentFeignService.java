package com.embrace.springcloud.service;

import com.embrace.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @Author embrace
 * @Description
 * @Date create in  2020/11/28 19:59
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    // 这里用 @PathParam   会报错， 用 @Param
    @GetMapping(value = "payment/query/{id}")
    CommonResult queryPayment(@RequestParam(value = "id") long  id);

    @GetMapping(value = "payment/timeout")
    String queryTimeout();
}
