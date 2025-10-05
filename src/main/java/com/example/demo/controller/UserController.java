package com.example.demo.controller;


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
    public User CreateUser(@RequestBody User user) {

        return userService.CreateUser(user);

    }


    @PutMapping("/{id}")
    public User Update(@PathVariable Long id, @RequestBody User user) {

        return userService.Update(id, user);
    }


    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

@GetMapping("/{id}")
    public User getFindByUser(@PathVariable Long id) {
       return userService.getFindByUser(id);
    }


}
