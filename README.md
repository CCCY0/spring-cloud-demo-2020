# 总体结构

## 通用的模块
cloud-api-commons: 通用的模块   

## 注册中心
cloud-eureka-server-7001: eureka注册中心1   
cloud-eureka-server-7002: eureka注册中心2   

## 服务提供者或消费者
cloud-provider-payment-8001: eureka服务提供者1   
cloud-provider-payment-8002: eureka服务提供者2   
cloud-consumer-order-80: eureka服务消费者(带ribbon),调用"eureka服务提供者"   
cloud-consumer-order-feign-80: eureka服务消费者(带openfeign),调用"eureka服务提供者"   

## 使用zk注册的服务
cloud-provider-payment-8004: zk服务提供者   
cloud-consumer-zk-order-80: zk服务消费者,调用"zk服务提供者"   

## 使用consul注册的服务
cloud-provider-payment-8006: consul服务提供者   
cloud-consumer-consul-order-80: consul服务消费者,调用"consul服务提供者"   

## 带Hystrix的服务
cloud-provider-hystrix-payment-8001: eureka服务提供者(Hystrix)   
cloud-consumer-order-feign-hystrix-80: eureka服务消费者(Hystrix)   
cloud-consumer-hystrix-dashboard-9001: HystrixDashBoard监控面板   

## 网关
cloud-gateway-9527: 统一入口网关, 进行请求转发   

## 配置中心和客户端
cloud-config-center-3344: config-server服务   
cloud-config-client-3355: 从config-server获取配置的客户端1   
cloud-config-client-3366: 从config-server获取配置的客户端2   

## cloud stream
cloud-stream-rabbitmq-provider-8801: 生产者   
cloud-stream-rabbitmq-consumer-8802: 消费者1   
cloud-stream-rabbitmq-consumer-8803: 消费者2   

## sleuth+zipkin 服务调用链路
cloud-zipkin-server-7777: zipkin-server (cloud-provider-payment-8001和cloud-consumer-order-80已经引入zipkin配置)   

## nacos作为注册中心
cloud-alibaba-provider-payment-8008: nacos服务提供者1   
cloud-alibaba-provider-payment-8009: nacos服务提供者2(只是加了个-Dserver.port=8009参数)   
cloud-alibaba-consumer-nacos-order-83: nacos服务消费者(带ribbon),调用"nacos服务提供者"   

## nacos作为配置中心
cloud-alibaba-config-nacos-client-3377: 从nacos配置中心获取配置的客户端   

## 带sentinel的服务
cloud-alibaba-provider-payment-sentinel-8401: 测试服务限流,降级(使用sentinel自带的返回结果)   
cloud-alibaba-provider-payment-sentinel-8010: 带自定义sentinel返回的服务提供者1   
cloud-alibaba-provider-payment-sentinel-8011: 带自定义sentinel返回的服务提供者2   
cloud-alibaba-consumer-nacos-sentinel-order-84: 带feign+sentinel的服务消费者   

## seata分布式事务
cloud-alibaba-seata-order-service-2001: 订单服务,调用库存服务和账户服务   
cloud-alibaba-seata-storage-service-2002: 库存服务   
cloud-alibaba-seata-account-service-2003: 账户服务   

# docker创建命令
docker run -d -p 2181:2181 --name zookeeper zookeeper  
docker run -d -p 8500:8500 --name consul consul  
docker run -d -p 8848:8848 --name=nacos --env MODE=standalone nacos/nacos-server  
docker run -d -p 5671:5671 -p 5672:5672 -p 15672:15672 --name rabbitmq rabbitmq  
docker run -d -p 8858:8858 --name sentinel bladex/sentinel-dashboard  
(PS: 因seata获取的IP地址为内网IP, 所以需要使用SEATA_IP环境变量手动配置一下注册到nacos的IP地址)  
docker run --network host --name seata-server -d -p 8091:8091 -e SEATA_IP=192.168.91.128 -e SEATA_CONFIG_NAME=file:/root/seata-config/registry -v /root/docker/seata:/root/seata-config seataio/seata-server  

