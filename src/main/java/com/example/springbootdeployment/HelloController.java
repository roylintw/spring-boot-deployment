package com.example.springbootdeployment;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String landingPage() {
        return "這是一個 Google Cloud 專案!";
    }

    @GetMapping("/hello")
    public String hello(Authentication authentication) {
        String userName = authentication.getName();
        return "您好! " + userName + " 先生/女士";
    }

}
