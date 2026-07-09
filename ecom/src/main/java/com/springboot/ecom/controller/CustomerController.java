package com.springboot.ecom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @GetMapping("/api/hello")
    public String sayHello(){
        return "Hello Spring Boot";
    }

    @GetMapping("/api/hello/private")
    public String sayPrivateHello(){
        return "Hello Spring Boot in private";
    }
}
