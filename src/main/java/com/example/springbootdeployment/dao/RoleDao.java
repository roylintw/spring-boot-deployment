package com.example.springbootdeployment.dao;

import com.example.springbootdeployment.vo.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleDao extends CrudRepository<Role, Integer>  {

    // 權限相關
    @Query(value = "SELECT role.role_id, role.role_name FROM role " +
            "JOIN member_has_role ON role.role_id = member_has_role.role_id " +
            "WHERE member_has_role.member_id = ?1", nativeQuery = true)
    List<Role> getRolesByMemberId(Integer memberId);

}
