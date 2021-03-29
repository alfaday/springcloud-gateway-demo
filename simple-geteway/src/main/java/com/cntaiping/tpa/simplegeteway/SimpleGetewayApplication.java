package com.cntaiping.tpa.simplegeteway;


import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableApolloConfig
public class SimpleGetewayApplication {

    public static void main(String[] args) {
        System.setProperty("app.id","gateway");
        System.setProperty("env","dev");
        System.setProperty("apollo.meta","http://127.0.0.1:8080");
        SpringApplication.run(SimpleGetewayApplication.class, args);
    }


}
