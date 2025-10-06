package com.example.demo.repository;

import com.example.demo.entity.Account;
import com.example.demo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    List<Transaction> findByAccountAndDateBetween(Account account,
                                                  LocalDateTime startDate,
                                                  LocalDateTime endDate);

    @Query("SELECT m FROM Transaction m WHERE m.account.account_id = :accountId ")
    List<Transaction> findByAccountId(Long accountId);    //association (Account)    // Id property
//findByAccountAccount_id

}



