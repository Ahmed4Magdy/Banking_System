package com.example.demo.service;


import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User CreateUser(User user) {

        return userRepository.save(user);

    }


    public User Update(Long id, User user) {

        User userexist = userRepository.findById(id).orElseThrow(() -> new RuntimeException("not found user with " + id));

        userexist.setEmail(user.getEmail());
        userexist.setName(user.getName());
        userexist.setPassword(user.getPassword());
        userexist.setRole(user.getRole());
        return userRepository.save(userexist);
    }



    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    public User getFindByUser(Long id){
        User userexist =userRepository.findById(id).orElseThrow(()->new RuntimeException("not found user with"+id));
        return userexist;
    }



}
