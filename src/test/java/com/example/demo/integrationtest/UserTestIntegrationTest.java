package com.example.demo.integrationtest;


import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest //load all beans services ,repository
@AutoConfigureMockMvc
@Transactional
public class UserTestIntegrationTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserDto dto;
    @BeforeEach
    void setup(){

        userRepository.deleteAll(); // before each test delete anything in db and become clean

        dto = new UserDto();
        dto.setEmail("ahmed@gmail.com");
        dto.setPassword(passwordEncoder.encode("ww33242"));
        dto.setRole(User.Role.CUSTOMER);



    }



    @Test
    void testCreateUser() throws Exception {

        mockMvc.perform(post("/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)))// convert java object to

                .andExpect(status().isOk());

        assertEquals(1,userRepository.count());

    }


}
