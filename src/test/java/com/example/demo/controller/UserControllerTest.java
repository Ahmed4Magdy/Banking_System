package com.example.demo.controller;


import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.service.impl.UserServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jboss.jandex.JandexAntTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {


    private MockMvc mockMvc;

    @Mock
    private UserServiceInterface userServiceInterface;

    @InjectMocks
    private UserController userController;

    private UserDto dto;

    @BeforeEach
    void setup() {

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        dto = new UserDto();
        dto.setId(1L);
        dto.setEmail("ahmed@gmail.com");

    }


    @Test
    void testCreateUser() throws Exception {

        when(userServiceInterface.CreateUser(any(UserDto.class))).thenReturn(dto);

        mockMvc.perform(post("/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk());

    }


    @Test
    void testUpdateUser() throws Exception {

        when(userServiceInterface.Update(eq(1L), any(UserDto.class))).thenReturn(dto);

        mockMvc.perform(put("/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk());


    }


    @Test
    void testgetAllUser() throws Exception {

        when(userServiceInterface.getAllUsers()).thenReturn(List.of(dto));

        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].email").value("ahmed@gmail.com"));


    }


    @Test
    void testgetByUser() throws Exception {

        when(userServiceInterface.getFindByUser(1L)).thenReturn(dto);

        mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk());


    }



}
