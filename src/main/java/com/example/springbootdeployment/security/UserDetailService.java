package com.example.springbootdeployment.security;

import com.example.springbootdeployment.dao.MemberDao;
import com.example.springbootdeployment.dao.RoleDao;
import com.example.springbootdeployment.vo.Member;
import com.example.springbootdeployment.vo.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    @Autowired
    private RoleDao roleDao;

    private final static Logger log = LoggerFactory.getLogger(UserDetailService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 從資料庫查詢會員資料
        Member member = memberDao.findByAccount(username);

        if (member == null) {
            throw new UsernameNotFoundException("Member not found for: " + username);
        } else {

            log.info("Member {} login successful!", username);

            String account = member.getAccount();
            String password = member.getPassword();

            // 權限
            List<Role> roleList = roleDao.getRolesByMemberId(member.getMemberId());
            System.out.println("!!!");
            // 將 Role 轉換成 GrantedAuthority 格式
            List<GrantedAuthority> authorities = convertToAuthorities(roleList);

            // 轉換成 Spring Security 指定的 User 格式
            return new User(account, password, authorities);
        }
    }

    /**
     * 將 Role 轉換成 GrantedAuthority 格式
     *
     * @param roleList
     * @return List<GrantedAuthority>
     */
    private List<GrantedAuthority> convertToAuthorities(List<Role> roleList) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roleList) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return authorities;
    }

}
