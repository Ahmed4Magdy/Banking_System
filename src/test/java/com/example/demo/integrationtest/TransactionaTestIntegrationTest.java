package com.example.demo.integrationtest;

import com.example.demo.dto.AccountDto;
import com.example.demo.dto.TransactionDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Account;
import com.example.demo.entity.Transaction;
import com.example.demo.entity.User;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class TransactionaTestIntegrationTest {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;

    private TransactionDto dto1;
    private Account account;
    private User user;

    @BeforeEach
    void setup() {

        transactionRepository.deleteAll();
        userRepository.deleteAll();
        accountRepository.deleteAll();

        User user1 = new User();
        user1.setName("Ahmed");
        user1.setEmail("ahmed@example.com");
        user1.setPassword("123");
        userRepository.save(user1);

        account = new Account();
        account.setAccount_id(1L);
        account.setType(Account.accountType.CURRENT);
        account.setUser(user);
        accountRepository.save(account);


        dto1 = new TransactionDto();
        dto1.setAccountId(1L);
        dto1.setAmount(100.0);
        dto1.setAccountId(1L);


    }


    @Test
    void testCreateTransaction() throws Exception {

        mockMvc.perform(post("/transaction/deposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto1)))
                .andExpect(status().isOk());

        assertEquals(100.0,dto1.getAmount());
    }


}
