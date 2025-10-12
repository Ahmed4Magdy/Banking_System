package com.example.demo.controller;


import com.example.demo.dto.UserDto;
import com.example.demo.service.impl.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceInterface userServiceInterface;


    @PostMapping("/create")
    public UserDto CreateUser(@RequestBody UserDto dto) {

        return userServiceInterface.CreateUser(dto);

    }


    @PutMapping("/{id}")
    public UserDto Update(@PathVariable Long id, @RequestBody UserDto dto) {

        return userServiceInterface.Update(id, dto);
    }


    @GetMapping("")
    public List<UserDto> getAllUsers() {
        return userServiceInterface.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getFindByUser(@PathVariable Long id) {
        return userServiceInterface.getFindByUser(id);
    }






}
