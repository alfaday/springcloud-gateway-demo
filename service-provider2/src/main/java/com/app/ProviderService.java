package com.app;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
public class ProviderService {


    @ResponseBody
    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String findPerson(){
//        List<InstanceInfo> instances = client.getInstancesById("serviceProvider");
//        System.out.println("host=" + instances.get(0).getIPAddr() +",service id =" + instances.get(0).getId());
        Person person = new Person();
        person.setId(1);
        person.setAge(18);
        person.setName("mike");
        System.out.println("$$$$$$$$  req deal sus!!");
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
        System.out.println(100/a);
        System.out.println("$$$$$$$$  req deal sus!!");
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

