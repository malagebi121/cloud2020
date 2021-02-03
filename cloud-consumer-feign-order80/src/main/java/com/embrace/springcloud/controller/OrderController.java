package com.embrace.springcloud.controller;

import com.embrace.springcloud.entities.CommonResult;
import com.embrace.springcloud.entities.PaymentDto;
import com.embrace.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author embrace
 * @Description
 * @Date create in  2020/11/28 19:58
 */
@RestController
@RequestMapping("consumer/payment")
@Slf4j
public class OrderController {

    @Resource
    private PaymentFeignService paymentService;

    @GetMapping(value = "/query/{id}")
    public CommonResult<PaymentDto>  queryPayment(@PathVariable long  id){
        log.info("查询：" + id);
        return  paymentService.queryPayment(id);
    }

    @GetMapping(value = "/timeout")
    public String  queryTimeout(){
        log.info("超时查询");
        return  paymentService.queryTimeout();
    }

}
