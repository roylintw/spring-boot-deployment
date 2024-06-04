package com.example.springbootdeployment.dao;

import com.example.springbootdeployment.vo.MemberHasRole;
import org.springframework.data.repository.CrudRepository;

public interface MemberHasRoleDao extends CrudRepository<MemberHasRole, Integer>  {
}
