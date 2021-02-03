package com.embrace.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @Author embrace
 * @Description
 * @Date create in  2020/11/29 21:13
 */
@Component  //这个少加了也会报错，不能启动
public class PaymentFallbackService  implements  PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentFallbackService   service  fallBack  paymentInfo_OK ";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "PaymentFallbackService   service  fallBack  paymentInfo_TimeOut ";
    }
}
