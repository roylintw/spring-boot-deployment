package com.example.springbootdeployment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;

@Data
public class MemberRequest {

    @NotEmpty
    String account;

    @NotEmpty
    String password;

    @NotBlank
    String memberName;

    String email;

    String gender;

    String region;

    String address;

    Date birthDate;
}
