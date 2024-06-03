package com.example.springbootdeployment.controller;

import com.example.springbootdeployment.dao.MemberDao;
import com.example.springbootdeployment.dto.MemberRequest;
import com.example.springbootdeployment.service.MemberService;
import com.example.springbootdeployment.vo.Member;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @Autowired
    private MemberDao memberDao;

    public static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @PostMapping("/register")
    public ResponseEntity<Member> createMember(@RequestBody @Valid MemberRequest memberRequest) {
        // 檢查帳號是否註冊過
        Member existMember = memberDao.findByAccount(memberRequest.getAccount());
        if (existMember != null) {
            logger.info("帳號已註冊過");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 新增會員
        Member member = memberService.createMember(memberRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }

}
