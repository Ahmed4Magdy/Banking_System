package com.example.demo.dto;

import com.example.demo.entity.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {


    private String name;

    private String email;

    private UserRole role;


}
