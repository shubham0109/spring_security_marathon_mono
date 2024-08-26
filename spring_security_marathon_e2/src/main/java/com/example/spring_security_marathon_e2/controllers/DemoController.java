package com.example.spring_security_marathon_e2.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/test/{smth}")
    @PreAuthorize("@testEndpointAuthorizationManager.authorize(#TestEndpointAuthorizationManager.#Type.#A, #smth)")
    public String test(@PathVariable String smth){
        System.out.println("IN TEST");
        return "test";
    }

    @GetMapping("/demo")
    public String demo(){
        System.out.println("IN DEMO");
        return "demo";
    }
}
