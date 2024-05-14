package com.example.springbootdeployment.service.impl;

import com.example.springbootdeployment.dao.MemberRepository;
import com.example.springbootdeployment.dto.MemberRequest;
import com.example.springbootdeployment.service.MemberService;
import com.example.springbootdeployment.vo.Member;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Member createMember(MemberRequest memberRequest) {
        ModelMapper modelMapper = new ModelMapper();
        Member member = modelMapper.map(memberRequest, Member.class);
        member.setCreateDate(new Date());
        memberRepository.save(member);

        return memberRepository.findByMemberId(member.getMemberId());
    }
}
