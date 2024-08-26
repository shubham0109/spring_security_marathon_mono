package com.example.spring_security_marathon_e3.controllers;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class TestController {


    @GetMapping("/test1")
    @PreAuthorize("hasAuthority(read)")
    public String test1(){
        System.out.println("test1");
        return "test1";
    }

    @GetMapping("/test2")
    @PostAuthorize("hasAuthority(write) && returnObject == test3")   // the method will run, but it won't return if authorization fails
    public String test2(){
        System.out.println("test2");
        return "test2";
    }

    @GetMapping("/test3")
    @PreFilter("filterObject.contains('a')")
    public void test3(@RequestBody List<String> l){
        l.forEach(System.out::println);
    }

    @GetMapping("/test4")
    @PostFilter("filterObject.contains('a')")
    public List<String> test4(){
        List<String> l = new ArrayList<>(Arrays.asList("asdf", "qwerty"));

        //List<String> l = List.of("asdf", "qwer"); // immutable -> doesn't worfk



        return l;
    }


}
