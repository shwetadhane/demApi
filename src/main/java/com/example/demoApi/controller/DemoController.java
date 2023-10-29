package com.example.demoApi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class DemoController {
    @GetMapping("/demo")
    public String function() {

        System.out.println("Rest endpoint get hit at : " + LocalDateTime.now());
        return "Welcome to AWS ";
    }

}
