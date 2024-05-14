package com.example.springbootdeployment.dao;


import com.example.springbootdeployment.vo.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Integer> {

    Member findByMemberId(Integer memberId);

}
