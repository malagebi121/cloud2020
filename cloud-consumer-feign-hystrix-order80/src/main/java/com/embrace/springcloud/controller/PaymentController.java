package com.embrace.springcloud.controller;

import com.embrace.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author embrace
 * @Description
 * @Date create in  2020/11/29 11:37
 */
@RestController
@RequestMapping("consumer/hystrix")
@DefaultProperties(defaultFallback = "payment_global_fallBack")  // 全局的异常处理
@Slf4j
public class PaymentController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    /**
     *   正常接口
     * @param id
     * @return
     */
    @GetMapping(value =  "/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfo_OK(id);
        log.info(result);
        return result;
    }

    /**
     * 超时接口
     *      ？  这里不知道问什么世界不起作用，有点点超时就直接报错了，有问题，暂时没有解决
     * @param id
     * @return
     */
    @GetMapping(value = "timeout/{id}")
    //单个指明的异常处理
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            })
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        log.info(result);
        return result;
    }


    //全局的异常处理
    @GetMapping(value = "global/{id}")
    @HystrixCommand
    public String paymentInfo_TimeOut_all(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        log.info(result);
        return result;
    }


    public String paymentInfo_TimeOutHandler(@PathVariable("id") Integer id){
        log.info("请求超时：fallback");
        return   "我是消费者80，对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己，o(╥﹏╥)o";

    }

    //全局的 global fallback
    public String payment_global_fallBack(){
        log.info("fallback");
        return  "global 异常处理信息，请稍后再试！";
    }







}
