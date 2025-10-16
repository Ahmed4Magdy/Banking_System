package com.example.demo.controller;


import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userServiceInterface;


    @PostMapping("/create")
    public UserDto CreateUser(@Valid @RequestBody UserDto dto) {

        return userServiceInterface.CreateUser(dto);

    }


    @PutMapping("/{id}")
    public UserDto Update(@PathVariable Long id,@Valid @RequestBody UserDto dto) {

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
