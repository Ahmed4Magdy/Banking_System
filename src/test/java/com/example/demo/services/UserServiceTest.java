package com.example.demo.services;


import com.example.demo.dto.AccountDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;


    @InjectMocks
    private UserService userService;

    @Test
    void CreateUser_Success() {


        UserDto dto = new UserDto();
        dto.setEmail("ahmed@gmail.com");
        User user = new User(1L, "Ahmed", "ahmed@gmail.com", null, null, null);

        when(userMapper.toEntity(dto)).thenReturn(user);
        when(passwordEncoder.encode(dto.getPassword())).thenReturn(user.getPassword());
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(dto);


        UserDto result = userService.CreateUser(dto);

        assertNotNull(result);
        assertEquals(dto, result);


    }


    @Test
    void getUserById_success() {

        User user = new User(1L, null, null, null, null, null);
        UserDto dto = new UserDto();
        dto.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(dto);

        UserDto result = userService.getFindByUser(dto.getId());


        assertEquals(dto, result);


    }

}
