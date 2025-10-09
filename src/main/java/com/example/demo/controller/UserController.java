package com.example.demo.controller;


import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/create")
    public UserDto CreateUser(@RequestBody UserDto dto) {

        return userService.CreateUser(dto);

    }


    @PutMapping("/{id}")
    public UserDto Update(@PathVariable Long id, @RequestBody UserDto dto) {

        return userService.Update(id, dto);
    }


    @GetMapping("")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getFindByUser(@PathVariable Long id) {
        return userService.getFindByUser(id);
    }






}
