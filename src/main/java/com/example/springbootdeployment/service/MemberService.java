package com.example.springbootdeployment.service;

import com.example.springbootdeployment.dto.MemberRequest;
import com.example.springbootdeployment.vo.Member;

public interface MemberService {

    Member createMember(MemberRequest memberRequest);

}
