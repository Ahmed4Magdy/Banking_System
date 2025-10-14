//package com.example.demo.repository;
//
//
//import com.example.demo.entity.Account;
//import com.example.demo.entity.MonthlyStatement;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//public class MonthlyStatementTest {
//
//
//    @Autowired
//    private AccountRepository accountRepository;
//
//    @Autowired
//    private MonthlyStatementRepository monthlyStatementRepository;
//
//
//    private MonthlyStatement statement;
//    private Account account;
//
//    @BeforeEach
//    void setup() {
//
//        account = new Account();
//        account.setAccount_number("xx33");
//        accountRepository.save(account);
//
//
//        statement = new MonthlyStatement();
//        statement.setAccount(account);
//        statement.setMonth("2025-10");
//        statement.setSumdeposit(2000.0);
//        statement.setSumwithdraw(500.0);
//        statement.setCreatedAt(LocalDate.now());
//        monthlyStatementRepository.save(statement);
//
//
//    }
//
//
//    //have error beacuse monthlyStatement table
//
////    @Test
////    void testfindByAccountAndDateBetween(){
////
////
////        MonthlyStatement monthlyStatement =monthlyStatementRepository.findByAccountIdAndMonth(account.getAccount_id(),"2025-10");
////
////        assertThat(monthlyStatement.getSumdeposit()).isEqualTo(2000.0);
////
////
////
////    }
//
//
//    @Test
//    void testCreateMonthly() {
//
//
//        MonthlyStatement statement1 = new MonthlyStatement();
//        statement1.setAccount(account);
//        statement1.setMonth("2025-09");
//        statement1.setSumdeposit(2000.0);
//        statement1.setSumwithdraw(500.0);
//        statement1.setCreatedAt(LocalDate.now());
//        monthlyStatementRepository.save(statement1);
//
//
//        assertThat(statement1.getSumdeposit()).isEqualTo(2000.0);
//
//
//    }
//
//}
