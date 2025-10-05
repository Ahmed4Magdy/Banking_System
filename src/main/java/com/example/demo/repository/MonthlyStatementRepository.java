package com.example.demo.repository;

import com.example.demo.entity.MonthlyStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface MonthlyStatementRepository extends JpaRepository<MonthlyStatement, Long> {

   @Query("SELECT m FROM MonthlyStatement m WHERE m.account.account_id = :accountId AND m.month = :month")
   MonthlyStatement findByAccountIdAndMonth(Long accountId, String month);


}