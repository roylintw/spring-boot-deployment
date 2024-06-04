package com.example.springbootdeployment.dao;

import com.example.springbootdeployment.vo.MemberHasRole;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MemberHasRoleDao extends CrudRepository<MemberHasRole, Integer>  {

    @Transactional
    @Modifying
    void deleteByMemberIdAndRoleId(@Param("memberId") Integer memberId, @Param("roleId") Integer roleId);

}
