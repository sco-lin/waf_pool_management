package com.maoxian.controller;

import com.maoxian.anno.ResponseNotIntercept;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    @ResponseNotIntercept
    public String hello() {
        return "hello";
    }
}
