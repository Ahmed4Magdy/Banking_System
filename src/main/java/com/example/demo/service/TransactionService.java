package com.example.demo.service;

import com.example.demo.dto.TransactionDto;

import java.util.List;

public interface TransactionService {

    TransactionDto Deposit(TransactionDto dto);

    TransactionDto Withdraw(TransactionDto dto);

    TransactionDto Transfer(TransactionDto dto);


     void DeleteTarnsaction(Long id);


    List<TransactionDto> getTransactionsByAccount(Long accountId);


}