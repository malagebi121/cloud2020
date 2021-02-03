package com.embrace.springcloud.controller;

import com.embrace.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author embrace
 * @Description
 * @Date create in  2020/11/28 22:50
 */
@RestController
@RequestMapping("payment/hystrix")
@Slf4j
public class PaymentHistrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    /**
     * 正常情况
     * @param id
     * @return
     */
    @GetMapping(value = "/ok/{id}")
    public String getOk(@PathVariable Integer id){
        log.info("请求正常连接："  +  id);
        String  result = paymentHystrixService.paymentInfo_OK(id);
        log.info(result);
        return  result;
    }

    /**
     *  超时
     * @param id
     * @return
     */
    @GetMapping(value = "/timeout/{id}")
    public String getTimeOut(@PathVariable Integer id){
        log.info("请求延时连接："  +  id + "秒");
        String  result  =  paymentHystrixService.paymentInfo_timeOut(id);
        log.info(result);
        return  result;
    }


    /**
     * 服务熔断
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentCircuitBreaker(id);
        log.info("------------result: " + result);
        return result;
    }
}
