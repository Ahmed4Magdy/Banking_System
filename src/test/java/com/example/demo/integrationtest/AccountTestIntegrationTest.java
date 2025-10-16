package com.example.demo.integrationtest;

import com.example.demo.dto.AccountDto;
import com.example.demo.entity.User;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class AccountTestIntegrationTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    private AccountDto dto;

    @BeforeEach
    void setup() {

        accountRepository.deleteAll();

        User user = new User();
        user.setId(1L);
        user.setEmail("ahmed@gmail.com");
        userRepository.save(user);


        dto = new AccountDto();
        dto.setAccount_number("ee331");
        dto.setAccount_balance(100.0);
        dto.setUserId(1L);


    }


    @Test
    void testCreateAccount() throws Exception {

        mockMvc.perform(post("/account/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))

                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.account_number").value("ee331"))
                .andExpect(jsonPath("$.account_balance").value(100.0));

        assertEquals(1, accountRepository.count());

    }


}
