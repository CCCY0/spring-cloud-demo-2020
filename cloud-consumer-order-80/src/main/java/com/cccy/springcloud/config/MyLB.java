package com.cccy.springcloud.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自己实现的负载均衡算法
 * @author Zhai
 * 2020/5/23 0:09
 */
@Configuration
public class MyLB {
    private AtomicInteger atomicInteger = new AtomicInteger();

    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
        } while (!atomicInteger.compareAndSet(current, next));
        System.out.println("*****next: " + next);
        return next;
    }

    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        // 当前调用次数 % 所有实例总数   取余数
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
