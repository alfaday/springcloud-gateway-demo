package com.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
public class ProviderService {

    private static Logger logger = LoggerFactory.getLogger(ProviderService.class);

    @ResponseBody
    @RequestMapping(value = "/info", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String findPerson(@RequestParam String id, @RequestBody UserVO userVO){
//        List<InstanceInfo> instances = client.getInstancesById("serviceProvider");
//        System.out.println("host=" + instances.get(0).getIPAddr() +",service id =" + instances.get(0).getId());
        logger.info("id value=" + id);
        logger.info("uservo=" + userVO.getName());
        Person person = new Person();
        person.setId(1);
        person.setAge(18);
        person.setName("mike");
        logger.info("$$$$$$$$  req deal sus!!");
        return "person";
    }

    @ResponseBody
    @RequestMapping(value = "/random", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String randomErr(){
        int a ;
        Random random = new Random();
        int x = random.nextInt() % 2;
        if(x == 1){
            a = 1;
        }else {
            a = 0;
        }
        logger.info(""+100/a);
        logger.info("$$$$$$$$  req deal sus!!");
        return "person";
    }


    private static class Person{
        private int id;
        private int age;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
