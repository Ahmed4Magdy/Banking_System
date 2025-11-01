package com.example.demo.service;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.LoginResponseDto;
import com.example.demo.dto.SignupDto;
import com.example.demo.dto.UserDto;

import java.util.List;

public interface UserService {


    UserDto CreateUser(UserDto dto);


    UserDto Update(Long id, UserDto dto);


    List<UserDto> getAllUsers();


    UserDto getFindByUser(Long id);

    SignupDto register(SignupDto dto);

    LoginResponseDto login (LoginDto loginDto);
}
