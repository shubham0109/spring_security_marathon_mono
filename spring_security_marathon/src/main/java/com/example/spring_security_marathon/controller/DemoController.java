package com.example.spring_security_marathon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {


    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @PostMapping("/demo")
    public String demo(){
        return "Demo";
    }


}
