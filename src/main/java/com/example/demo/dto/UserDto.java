package com.example.demo.dto;


import com.example.demo.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {


    private Long id;

    private String name;

    private String email;

    private String password;

    public User.Role role;


}
