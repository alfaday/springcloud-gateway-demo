package com.app;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class ConsumerService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private FeignClient feignClient;

    public String providerName = "serviceProvider";

    @ResponseBody
    @RequestMapping("/index")
    public String router() {
        // 根据应用名称调用服务
        String json = restTemplate.getForObject(
                "http://" + providerName + "/info", String.class);
        return json;
    }

    /**
     * 使用feign调用服务方的接口
     * @return
     */
    @ResponseBody
    @RequestMapping("/indexFeign")
    public String router2() {
        // 根据应用名称调用服务
        String json = feignClient.call();
        return json;
    }

    @ResponseBody
    @RequestMapping("/postTest")
    public String router3(@RequestBody UserVO user) {
        // 根据应用名称调用服务
        System.out.println("userid="+user.getId());
        return "ok";
    }

    /**
     * 客户端调用时使用断路器
     * @return
     */
    @HystrixCommand(fallbackMethod = "dealErr")
    @ResponseBody
    @RequestMapping("/indexHys")
    public String router4() {
        // 根据应用名称调用服务
        String json = restTemplate.getForObject(
                "http://" + providerName + "/random", String.class);
        return json;
    }

    public String dealErr(){
        return "Hystrix 容错处理";
    }

}
