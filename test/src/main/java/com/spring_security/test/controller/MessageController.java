package com.spring_security.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @GetMapping("/hello")
    public String getMessage(){
        return "hello there";
    }
}
