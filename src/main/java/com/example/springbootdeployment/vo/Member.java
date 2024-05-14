package com.example.springbootdeployment.vo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "member")
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    Integer memberId;

    @NotEmpty
    @Column(name = "account")
    String account;

    @NotEmpty
    @Column(name = "password")
    String password;

    @NotBlank
    @Column(name = "member_name")
    String memberName;

    @Column(name = "email")
    String email;

    @Column(name = "gender")
    String gender;

    @Column(name = "region")
    String region;

    @Column(name = "address")
    String address;

    @Column(name = "birth_date")
    Date birthDate;

    @Column(name = "create_date")
    Date createDate;

}
