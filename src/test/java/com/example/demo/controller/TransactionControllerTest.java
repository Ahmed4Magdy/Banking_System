package com.example.demo.controller;


import com.example.demo.dto.TransactionDto;
import com.example.demo.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {


    private MockMvc mockMvc; //this built object to simulate http request as post ,get....

    @Mock
    private TransactionService transactionServiceInterface;

    @InjectMocks
    private TransactionController transactionController;

    private TransactionDto dto;

    @BeforeEach
    void setup() {

        dto = new TransactionDto();
        dto.setAccountId(1L);

        mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();

    }


    @Test
    void testDeposit() throws Exception {

        when(transactionServiceInterface.Deposit(any(TransactionDto.class))).thenReturn(dto);

        mockMvc.perform(post("/transaction/deposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))  //1 convert object to json

                .andExpect(status().isOk());
    }



    @Test
    void testWihdraw() throws Exception {

        when(transactionServiceInterface.Withdraw(any(TransactionDto.class))).thenReturn(dto);

        mockMvc.perform(post("/transaction/withdraw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))  //1 convert object to json

                .andExpect(status().isOk());
    }




    @Test
    void testTransfer() throws Exception {

        when(transactionServiceInterface.Transfer(any(TransactionDto.class))).thenReturn(dto);

        mockMvc.perform(post("/transaction/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))  //1 convert object to json

                .andExpect(status().isOk());
    }





}
