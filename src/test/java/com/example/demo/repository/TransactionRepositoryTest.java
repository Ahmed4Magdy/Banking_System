package com.example.demo.repository;


import com.example.demo.entity.Account;
import com.example.demo.entity.Transaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@DataJpaTest
public class TransactionRepositoryTest {


    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    private Transaction transaction1;
    private Account account;

    @BeforeEach
        //perform before each test
    void setup() {

        account = new Account();
        account.setAccount_number("aa12");
        accountRepository.save(account);


        transaction1 = new Transaction();
        transaction1.setAccount(account);
        transaction1.setType(Transaction.Type.DEPOSIT);
        transaction1.setAmount(1000);
        transaction1.setDate(LocalDate.of(2025, 10, 1).atStartOfDay());

        Transaction transaction2 = new Transaction();
        transaction2.setAccount(account);
        transaction2.setType(Transaction.Type.WITHDRAW);
        transaction2.setAmount(1000);
        transaction2.setDate(LocalDate.of(2025, 10, 18).atStartOfDay());

        transactionRepository.saveAll(List.of(transaction1, transaction2));


    }


    @Test
    void testfindByAccountAndDateBetween() {


        List<Transaction> transactions1 = transactionRepository.findByAccountId(account.getAccount_id());
        List<Transaction> transactions2 = transactionRepository.findByAccountAndDateBetween(account, LocalDate.of(2025, 10, 1).atStartOfDay(), LocalDate.of(2025, 10, 18).atStartOfDay());

        Optional<Transaction> transaction1 = transactionRepository.findById(1L);

        assertThat(transactions1).hasSize(2);
        assertThat(transactions2).hasSize(2);

        assertThat(transaction1).isPresent();
    }


    @Test
    void testcreateTransaction() {


        Transaction transactions = new Transaction();
        transactions.setAccount(account);
        transactions.setType(Transaction.Type.WITHDRAW);
        transactions.setAmount(1000.0);
        transactionRepository.save(transactions);

        assertThat(transactions.getAmount()).isEqualTo(1000.0);
    }


}
