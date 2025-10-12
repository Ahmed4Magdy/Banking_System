package com.example.demo.controller;

import com.example.demo.dto.AccountDto;
import com.example.demo.service.impl.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {


    private MockMvc mockMvc;
    @Mock
    private AccountService accountService;


    private AccountDto accountDto;

    @BeforeEach
    void setup() {
        AccountController controller = new AccountController(accountService); // ✅ مرر الـ mock هنا
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        accountDto = new AccountDto();
        accountDto.setUserId(1L);
        accountDto.setAccount_id(1L);
        accountDto.setAccount_number("AC123");
        accountDto.setAccount_balance(2000.0);

    }


    @Test
    void testCreateAccount() throws Exception {

        when(accountService.CreatAccount(any(AccountDto.class))).thenReturn(accountDto);

        mockMvc.perform(post("/account/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(accountDto))) //ObjectMapper convert java object to json as body and after that will create new object and with different address

                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.account_number").value("AC123"))
                .andExpect(jsonPath("$.account_balance").value(2000.0));

    }


    @Test
    void testGetAccountById() throws Exception {

        when(accountService.getAccountDetailsWithAccountNumber(1L)).thenReturn(accountDto);

        mockMvc.perform(get("/account/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.account_number").value("AC123"))
                .andExpect(jsonPath("$.account_balance").value(2000.0));

    }


    @Test
    void testGetAllAccounts() throws Exception {

        when(accountService.getAllAccountDetails()).thenReturn(List.of(accountDto));

        mockMvc.perform(get("/account"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].account_number").value("AC123"))
                .andExpect(jsonPath("$.[0].account_balance").value(2000.0));

    }


    @Test
    void testUpdateAccount() throws Exception {

        when(accountService.updateAccount(eq(1L), any(AccountDto.class))).thenReturn(accountDto);

        mockMvc.perform(put("/account/modfiyaccount/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(accountDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.account_number").value("AC123"))
                .andExpect(jsonPath("$.account_balance").value(2000.0));

    }
// eq will use when exist matcher 1L ,any and if alone will use 1L direct





@Test
void testDeleteAccount() throws Exception {

    doNothing().when(accountService).CloseAccount(1L);

    mockMvc.perform(delete("/account/1"))
            .andExpect(status().isOk());

}

}