package com.example.springbootdeployment.dao;

import com.example.springbootdeployment.vo.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberDao extends CrudRepository<Member, Integer> {

    /**
     * @param account 帳號
     * @return Member
     */
    Member findByAccount(String account);

}
