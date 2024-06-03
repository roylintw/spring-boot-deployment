package com.example.springbootdeployment.security;

import com.example.springbootdeployment.dao.MemberDao;
import com.example.springbootdeployment.vo.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDetailService implements UserDetailsService {

    @Autowired
    private MemberDao memberDao;

    private final static Logger log = LoggerFactory.getLogger(UserDetailService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 從資料庫查詢會員資料
        Member member = memberDao.findByAccount(username);

        if(member == null){
            throw new UsernameNotFoundException("Member not found for: " + username);
        } else {

            log.info("Member {} login successful!", username);

            String account = member.getAccount();
            String password = member.getPassword();

            // 權限
            List<GrantedAuthority> authorities = new ArrayList<>();

            // 轉換成 Spring Security 指定的 User 格式
            return new User(account, password, authorities);
        }
    }

}
