package com.example.springbootdeployment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    public static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/")
    public String landingPage() {
        return "這是一個 Google Cloud 專案!";
    }

    @GetMapping("/hello")
    public String hello(Authentication authentication) {
        String userName = authentication.getName();
        logger.info("使用者 " + userName + " 登入中...");
        return "您好! " + userName + " 先生/女士";
    }

}
