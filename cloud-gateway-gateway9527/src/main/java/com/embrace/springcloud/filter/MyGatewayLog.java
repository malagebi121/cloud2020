package com.embrace.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @Author embrace
 * @Description   自定义 gateway 过滤器，和spring mvc里面 HandlerInterceptorAdapter  差不多，  token鉴权 ， 日志 都是这些
 * @Date create in  2020/11/30 16:59
 */
@Component
@Slf4j
public class MyGatewayLog implements GlobalFilter,Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, GatewayFilterChain gatewayFilterChain) {
        log.info("------------ come in MyLogGateWayFilter: " + new Date());
        String name = serverWebExchange.getRequest().getQueryParams().getFirst("name");
        if (StringUtils.isEmpty(name)) {
            log.info("------------ 用户名 [{}] 非法 o(╥﹏╥)o", name);
            serverWebExchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return serverWebExchange.getResponse().setComplete();
        }
        return gatewayFilterChain.filter(serverWebExchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
