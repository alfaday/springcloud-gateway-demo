package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class Consumer {

    @Autowired
    private RestTemplate restTemplate;

    @ResponseBody
    @RequestMapping("/index")
    public String router() {
        // 根据应用名称调用服务
        String json = restTemplate.getForObject(
                "http://serviceProvider/info", String.class);
        return json;
    }
}
