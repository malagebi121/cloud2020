package com.embrace.springcloud.controller;

import com.embrace.springcloud.entities.CommonResult;
import com.embrace.springcloud.entities.PaymentDto;
import com.embrace.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @Author embrace
 * @Description
 * @Date create in  2020/11/25 22:24
 */
@RestController
@Slf4j
@RequestMapping("payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    //集群配置，方便返回哪一台机子
    @Value("${server.port}")
    private String  serverPort;

    @PostMapping(value = "add")
    public CommonResult addPayment(@RequestBody  PaymentDto paymentDto){
        int  result = paymentService.save(paymentDto);
        log.info("=== 插入数据:" +result );
        if(result > 0){
            return new CommonResult(200, "插入成功,端口：" + serverPort, result);
        }
        return new CommonResult(500, "插入失败，端口：" + serverPort, null);
    }

    @GetMapping(value = "query/{id}")
    public CommonResult queryPayment(@PathVariable  long  id){
        PaymentDto  paymentDto = paymentService.getPaymentById(id);
        log.info("=== 查询:" + id);
        if(paymentDto != null){
            return new CommonResult(200, "查询成功，端口：" + serverPort, paymentDto);
        }
        return new CommonResult(500, "查询失败，端口：" + serverPort, null);
    }

    // feign   超时调用测试
    @GetMapping(value = "timeout")
    public String queryTimeout(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  serverPort;
    }



    //网关lb
    @GetMapping(value =  "lb")
    public String getLb(){
        return  serverPort;
    }

}
