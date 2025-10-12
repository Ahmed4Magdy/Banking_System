package com.example.demo.service.impl;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;

import java.util.List;

public interface UserServiceInterface {


    UserDto CreateUser(UserDto dto);


    UserDto Update(Long id, UserDto dto);


    List<UserDto> getAllUsers();


    UserDto getFindByUser(Long id);


}
