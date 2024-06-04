package com.example.springbootdeployment.vo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "member_has_role")
@Data
public class MemberHasRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_has_role_id")
    private Integer memberHasRoleId;

    @Column(name = "member_id")
    private Integer memberId;

    @Column(name = "role_id")
    private Integer roleId;

}
