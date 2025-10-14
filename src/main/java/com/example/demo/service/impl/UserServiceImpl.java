package com.example.demo.service.impl;


import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto CreateUser(UserDto dto) {

        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
//        User saved=userRepository.save(user);
//        UserDto dto1 =userMapper.toDto(saved);
//        return dto1;
        userRepository.save(user);
        return userMapper.toDto(user);

    }



    // 1 2 4
    public UserDto Update(Long id, UserDto dto) {

        User userexist = userRepository.findById(id).orElseThrow(() -> new RuntimeException("not found user with " + id));

        userMapper.updateUserFromDto(dto,userexist);  //update name,role,email and ignore password because no touch otherwise if user need change password
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            userexist.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        userRepository.save(userexist);

        UserDto userDto = userMapper.toDto(userexist);
        return userDto;


    }


    // 🔵 Get all users
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserDto getFindByUser(Long id) {
        User userexist = userRepository.findById(id).orElseThrow(() -> new RuntimeException("not found user with" + id));
        return userMapper.toDto(userexist);
    }


}
