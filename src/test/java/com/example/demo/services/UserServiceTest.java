package com.example.demo.services;


import com.example.demo.dto.LoginDto;
import com.example.demo.dto.LoginResponseDto;
import com.example.demo.dto.SignupDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.mapper.LoginMapper;
import com.example.demo.mapper.SignupMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private SignupMapper signupMapper;

    @Mock
    private LoginMapper loginMapper;

    @Mock
    private PasswordEncoder passwordEncoder;


    @InjectMocks
    private UserServiceImpl userService;


    @Test
    void register() {
        User user = new User(1L, "Ahmed", "ahmed@gmail.com", null, null, null);

        SignupDto dto = new SignupDto();
        dto.setEmail("ahmed@gmail.com");

        when(signupMapper.toEntity(dto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(signupMapper.toDto(user)).thenReturn(dto);


        SignupDto result = userService.register(dto);


        assertNotNull(result);


    }


    @Test
    void login() {

        User user = new User(1L, "Ahmed", "ahmed@gmail.com", null, null, null);
        LoginDto dto = new LoginDto();
        dto.setEmail("ahmed@gmail.com");
        LoginResponseDto dto1 =new LoginResponseDto();
        dto1.setEmail("ahmed@gmail.com");

        when(userRepository.findByEmail(dto.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(dto.getPassword(), user.getPassword())).thenReturn(true);

        when(loginMapper.toLoginResponseDto(user)).thenReturn(dto1);

        LoginResponseDto result =userService.login(dto);


    }


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
