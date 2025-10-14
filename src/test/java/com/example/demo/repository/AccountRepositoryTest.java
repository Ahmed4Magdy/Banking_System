package com.example.demo.repository;


import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest //spring test load jpa layer
public class AccountRepositoryTest {


    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;

    private User user;
    private Account account;

    @BeforeEach
    void setup() {

        user = new User();
        userRepository.save(user);
        account = new Account(1L, "AA12", 1000.0, Account.accountType.CURRENT, null, user, null, null);
        Account acc2 = new Account(2L, "acc456", 2000.0, Account.accountType.CURRENT, null, user, null, null);

        accountRepository.saveAll(List.of(account, acc2));

    }


    @Test
    void testFindAllAccountsForUser() {

        List<Account> account = accountRepository.findByUserId(user.getId());
        List<Account> account1 = accountRepository.findAll();
        assertThat(account1).hasSize(2);
        assertThat(account).hasSize(2);
        assertThat(account.get(0).getAccount_balance()).isEqualTo(1000.0);
//because exist more than account and each account index as account1=index 0 and etc...

    }


    @Test
    void testSaveAccount() {
        Account account = new Account(null, "acc789", 3000.0, Account.accountType.SAVINGS, LocalDate.now(), user, null, null);
        Account saved = accountRepository.save(account);

        assertThat(saved.getAccount_id()).isNotNull();
        assertThat(saved.getAccount_number()).isEqualTo("acc789");
    }


}
