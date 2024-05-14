package com.example.springbootdeployment.controller;

import com.example.springbootdeployment.dto.MemberRequest;
import com.example.springbootdeployment.service.MemberService;
import com.example.springbootdeployment.vo.Member;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<Member> createProduct(@RequestBody @Valid MemberRequest memberRequest) {
        Member member = memberService.createMember(memberRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }

}
