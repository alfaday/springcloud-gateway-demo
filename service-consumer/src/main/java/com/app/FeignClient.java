package com.app;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.cloud.openfeign.FeignClient(value = "serviceProvider") //value声明服务方的服务名称
public interface FeignClient {

    @GetMapping("/info") //要调用的服务方接口uri
    public String call();
}
