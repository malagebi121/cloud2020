package com.embrace.springcloud.controller;

import com.embrace.springcloud.entities.CommonResult;
import com.embrace.springcloud.entities.PaymentDto;
import com.embrace.springcloud.selfrule.LoadBalancer;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @Author embrace
 * @Description
 *
 *   ribbon
 *
 *
 * @Date create in  2020/11/26 16:32
 */
@RestController
@RequestMapping("consumer/payment")
@Slf4j
public class PaymentConsumerController {

    //单体应用写死UR测试，集群再用地址会报错
//    private static final String URL = "http://localhost:8001/";

    //集群指定服务名
    private static final String SERVICE_NAME = "http://CLOUD-PAYMENT-SERVICE/";

    private static final String SERVER = "CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private LoadBalancer loadBalancer;

    @GetMapping(value = "add")
    public CommonResult addPayment(PaymentDto paymentDto){
        log.info("========= 消费端调用添加数据:" + paymentDto.getSerial() );
        return restTemplate.postForObject(SERVICE_NAME + "payment/add", paymentDto, CommonResult.class);
    }

    @GetMapping(value = "query/{id}")
    public CommonResult queryPayment(@PathVariable String id){
        log.info("======== 消费端查询数据:" + id);
        return restTemplate.getForObject(SERVICE_NAME + "payment/query/" + id, CommonResult.class);
    }

    @GetMapping(value = "entity/query/{id}")
    public CommonResult selectPayment(@PathVariable String id){
        log.info("======== 消费层查新数据:" + id);

        //负载均衡原理，获取对应的服务器，  我这里怎么回是0 ，搞不懂  ，  第几次请求  n % li.size()  ，到哪台就是哪一台
        List<ServiceInstance> li  = discoveryClient.getInstances(SERVER);
        li.forEach(s -> log.info("得到所有服务器信息：" + s.getHost() + s.getInstanceId() + s.getServiceId() + s.getPort()));
        ResponseEntity<CommonResult> resultResponseEntity = restTemplate.getForEntity(SERVICE_NAME + "payment/query/" + id, CommonResult.class);
        if(resultResponseEntity.getStatusCode().is2xxSuccessful()){
            return  resultResponseEntity.getBody();
        }else{
            return new CommonResult(444,"查询错误");
        }
    }

    //利用自己的轮询算法实现负载均衡
    @GetMapping(value = "lb/{id}")
    public CommonResult selectPaymentLB(@PathVariable String id){
        log.info("======== 消费层查新数据:" + id);
        //负载均衡原理，获取对应的服务器，  我这里怎么回是0 ，搞不懂  ，  第几次请求  n % li.size()  ，到哪台就是哪一台
        List<ServiceInstance> li  = discoveryClient.getInstances(SERVER);
        ServiceInstance serviceInstance =  loadBalancer.getServiceInstance(li);
        if(li != null && !li.isEmpty() ){
            URI uri =   serviceInstance.getUri();
            ResponseEntity<CommonResult> resultResponseEntity = restTemplate.getForEntity(uri + "/payment/query/" + id, CommonResult.class);
            if(resultResponseEntity.getStatusCode().is2xxSuccessful()){
                return  resultResponseEntity.getBody();
            }else{
                return new CommonResult(444,"查询错误");
            }
        }
        return new CommonResult(444,"查询错误");
    }



    /**
     * P94用,sleuth+zipkin链路监控使用
     *
     * @return
     */
    @GetMapping(value =  "zipkin")
    public String paymentZipkin() {
        String result = restTemplate.getForObject("http://localhost:8001" + "/payment/zipkin/", String.class);
        return result;
    }

}
