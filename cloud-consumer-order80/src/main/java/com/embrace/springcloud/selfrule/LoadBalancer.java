package com.embrace.springcloud.selfrule;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @Author embrace
 * @Description   手写轮询算法接口
 * @Date create in  2020/11/28 14:39
 */

public interface LoadBalancer {
     ServiceInstance  getServiceInstance(List<ServiceInstance> list);
}
