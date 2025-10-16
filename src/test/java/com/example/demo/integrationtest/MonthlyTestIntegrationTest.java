package com.example.demo.integrationtest;


import com.example.demo.dto.MonthlyStatementDto;
import com.example.demo.entity.Account;
import com.example.demo.entity.Transaction;
import com.example.demo.entity.User;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.MonthlyStatementRepository;
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

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class MonthlyTestIntegrationTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    MonthlyStatementRepository monthlyStatementRepository;

    private Account account;
    private Transaction t2;
    private MonthlyStatementDto m;

    @BeforeEach
    void setup() {
        transactionRepository.deleteAll();
        accountRepository.deleteAll();
        userRepository.deleteAll();

        User user = new User();
        user.setName("Sara");
        user.setEmail("sara@example.com");
        user.setPassword("pass");
        userRepository.save(user);

        account = new Account();
        account.setUser(user);
        account.setAccount_number("ACC100");
        account.setAccount_balance(3000.0);
        account.setType(Account.accountType.SAVINGS);
        accountRepository.save(account);

        // add some transactions
        Transaction t1 = new Transaction();
        t1.setAccount(account);
        t1.setAmount(500.0);
        t1.setType(Transaction.Type.DEPOSIT);
        t1.setDate(LocalDate.of(2025, 10, 1).atStartOfDay());
        transactionRepository.save(t1);

        t2 = new Transaction();
        t2.setAccount(account);
        t2.setAmount(200.0);
        t2.setType(Transaction.Type.WITHDRAW);
        t2.setDate(LocalDate.of(2025, 10, 18).atStartOfDay());
        transactionRepository.save(t2);


        m = new MonthlyStatementDto();
        m.setAccountId(1L);
        m.setOpeningBalance(2700.0);
        m.setMonth("2025-10");

    }


    @Test
    void testCreateMonthlyStatement() throws Exception {

        mockMvc.perform(post("/monthly/generate/2025-10/2025-10-18")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(m)))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.openingBalance").value(2700.0));
        assertEquals(1, monthlyStatementRepository.count());
    }


//    @Test
//    void testGetMonthlyStatement() throws Exception {
////        Long accountId = 1L;
////        String month = "2025-10";
//        mockMvc.perform(get("/monthly/" + account.getAccount_id() + "/2025-10"))
//
////        mockMvc.perform(get("/monthly/statement/" + accountId + "/" + month))
//                .andExpect(status().isOk());
//    }


}
