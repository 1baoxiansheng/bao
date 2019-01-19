package com.jk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

//消费者 需要到注册中心中去寻找生产者
@EnableDiscoveryClient//可以往服务中心进行注册
@SpringBootApplication
//可以调用服务中心的项目
@EnableFeignClients
public class ColoudConsumeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ColoudConsumeApplication.class, args);
    }

}

