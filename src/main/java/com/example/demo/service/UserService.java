package com.example.demo.service;

import com.example.demo.dto.UserDto;

import java.util.List;

public interface UserService {


    UserDto CreateUser(UserDto dto);


    UserDto Update(Long id, UserDto dto);


    List<UserDto> getAllUsers();


    UserDto getFindByUser(Long id);


}
