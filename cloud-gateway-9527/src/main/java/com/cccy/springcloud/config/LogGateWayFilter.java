package com.cccy.springcloud.config;

import cn.hutool.core.util.StrUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Zhai
 * 2020/5/28 0:50
 */
@Configuration
public class LogGateWayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        long millis = System.currentTimeMillis();
        System.out.println("******come in filter...");
        String username = exchange.getRequest().getQueryParams().getFirst("username");
        if (StrUtil.isBlank(username)) {
            System.out.println("******非法用户");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            System.out.println("过滤器消耗时间: " + (System.currentTimeMillis() - millis) + "毫秒");
        }));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
