package com.embrace.springcloud.selfrule;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author embrace
 * @Description
 * @Date create in  2020/11/28 14:40
 */
@Component
@Slf4j
public class MyRondomLB  implements LoadBalancer {

    //里面有一个AtomicInteger 表示这台服务器的请求次数
    private  AtomicInteger requestTimes;


    public MyRondomLB(){
        requestTimes = new AtomicInteger(0);
    }

    @Override
    public ServiceInstance getServiceInstance(List<ServiceInstance> list) {
        int  times = getAndIncreament();
        log.info("第" + times + "访问");
        return list.get(times % list.size());
    }

    public int getAndIncreament() {
        int current ;
        int next;
        do {
           current = this.requestTimes.get();
           next = current >= Integer.MAX_VALUE ? 0 : current + 1;
           if(this.requestTimes.compareAndSet(current,next)){
               break;
           }
        }while (true);

        //这样写也是可以的
//        do {
//            current = this.requestTimes.get();
//            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
//        }while (!this.requestTimes.compareAndSet(current,next));
        return   next;
    }
}
