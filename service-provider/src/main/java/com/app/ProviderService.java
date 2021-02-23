package com.app;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProviderService {


    @ResponseBody
    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String findPerson(){
        Person person = new Person();
        person.setId(1);
        person.setAge(18);
        person.setName("mike");
        try {
            Thread.sleep(1000*10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
