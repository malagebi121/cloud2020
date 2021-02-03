package com.embrace.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author embrace
 * @Description
 * @Date create in  2020/11/30 16:29
 */
@Configuration
public class GateWayConfig {

    /**
     * 配置一个ID为 path_route_george 的路由规则
     * 当访问地址:http://localhost:9527/guonei 时会自动转发到地址 http://news.baidu.com/guonei
     *
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator customeRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("path_route_george", r -> r.path("/guonei").uri("http://news.baidu.com/guonei"))
                .build();
    }


}
