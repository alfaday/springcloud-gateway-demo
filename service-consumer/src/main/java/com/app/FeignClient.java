package com.app;

import feign.Request;
import org.springframework.web.bind.annotation.*;

@org.springframework.cloud.openfeign.FeignClient(value = "serviceProvider") //value声明服务方的服务名称
public interface FeignClient {

    @RequestMapping(method = RequestMethod.POST,value = "/info") //要调用的服务方接口uri,feign实现post请求调用
    public String call(@RequestParam String id, @RequestBody UserVO userVO);
}
