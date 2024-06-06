package com.example.springbootdeployment.controller;

import com.example.springbootdeployment.dao.MemberDao;
import com.example.springbootdeployment.dao.MemberHasRoleDao;
import com.example.springbootdeployment.dao.RoleDao;
import com.example.springbootdeployment.dto.MemberRequest;
import com.example.springbootdeployment.service.MemberService;
import com.example.springbootdeployment.vo.Member;
import com.example.springbootdeployment.vo.MemberHasRole;
import com.example.springbootdeployment.vo.Role;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private MemberHasRoleDao memberHasRoleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @PostMapping("/register")
    public ResponseEntity<Member> createMember(@RequestBody @Valid MemberRequest memberRequest) {
        // 檢查帳號是否註冊過
        Member existMember = memberDao.findByAccount(memberRequest.getAccount());
        if (existMember != null) {
            logger.info("帳號已註冊過");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // hash 密碼
        String hashedPassword = passwordEncoder.encode(memberRequest.getPassword());
        memberRequest.setPassword(hashedPassword);

        // 新增會員
        Member member = memberService.createMember(memberRequest);

        if(member != null){
            // 為 Member 添加預設的 Role
            Role role = roleDao.getRoleByRoleName("ROLE_NORMAL_MEMBER");

            MemberHasRole memberHasRole = new MemberHasRole();
            memberHasRole.setMemberId(member.getMemberId());
            memberHasRole.setRoleId(role.getRoleId());
            memberHasRoleDao.save(memberHasRole);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }

    @PostMapping("/memberLogin")
//    @RequestMapping(value = "/memberLogin", method = RequestMethod.POST)
    public String memberLogin(Authentication authentication){
        System.out.println("登入中...");
        // 取得使用者的帳號
        String memberName = authentication.getName();

        // 取得使用者的權限

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        return "登入成功!帳號 " + memberName + " 的權限為: " + authorities;
    }

}
