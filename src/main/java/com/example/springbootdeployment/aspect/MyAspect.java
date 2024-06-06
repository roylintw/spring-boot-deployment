package com.example.springbootdeployment.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    @Before("execution (* com.example.springbootdeployment.controller.MemberController.*(..))")
    public void before(){
        System.out.println("I'm before");
    }

}
