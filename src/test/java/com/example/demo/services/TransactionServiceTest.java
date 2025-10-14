package com.example.demo.services;


import com.example.demo.dto.TransactionDto;
import com.example.demo.entity.Account;
import com.example.demo.entity.Transaction;
import com.example.demo.mapper.TransactionMapper;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;


    @Mock
    private TransactionMapper transactionMapper;

    @InjectMocks
    private TransactionServiceImpl transactionService;


    @Test
    void deposit_success() {

        Account account = new Account(1L, "ac12", 2000.0, Account.accountType.CURRENT, LocalDate.now(), null, null, null);

        TransactionDto dto = new TransactionDto();
        dto.setAccountId(1L);
        dto.setAmount(500.0);

        Transaction transaction = new Transaction();
        transaction.setAmount(500.0);
        transaction.setAccount(account);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(transactionMapper.toEntity(dto)).thenReturn(transaction);
        when(transactionRepository.save(transaction)).thenReturn(transaction);
        when(transactionMapper.toDTO(transaction)).thenReturn(dto);

        TransactionDto result =transactionService.Deposit(dto);

        assertEquals(dto, result);
        assertEquals(2500.0, account.getAccount_balance());


    }



    @Test
    void withdraw_success() {

        Account account = new Account(1L, "ac12", 2000.0, Account.accountType.CURRENT, LocalDate.now(), null, null, null);

        TransactionDto dto = new TransactionDto();
        dto.setAccountId(1L);
        dto.setAmount(500.0);

        Transaction transaction = new Transaction();
        transaction.setAmount(500.0);
        transaction.setAccount(account);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(transactionMapper.toEntity(dto)).thenReturn(transaction);
        when(transactionRepository.save(transaction)).thenReturn(transaction);
        when(transactionMapper.toDTO(transaction)).thenReturn(dto);

        TransactionDto result =transactionService.Withdraw(dto);

        assertEquals(dto, result);
        assertEquals(1500.0, account.getAccount_balance());


    }



    @Test
    void Transfer_success() {

        Account account1 = new Account(1L, "ac12", 2000.0, Account.accountType.CURRENT, LocalDate.now(), null, null, null);
        Account account2 = new Account(2L, "ac12", 3000.0, Account.accountType.CURRENT, LocalDate.now(), null, null, null);

        TransactionDto dto = new TransactionDto();
        dto.setAccountId(1L);
        dto.setTargetAccountId(2L);
        dto.setAmount(500.0);

        Transaction transaction = new Transaction();
        transaction.setAmount(500.0);
        transaction.setAccount(account1);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account1));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(account2));

        when(transactionMapper.toEntity(dto)).thenReturn(transaction);
        when(transactionRepository.save(transaction)).thenReturn(transaction);
        when(transactionMapper.toDTO(transaction)).thenReturn(dto);

        TransactionDto result =transactionService.Transfer(dto);

        assertEquals(dto, result);
        assertEquals(1500.0, account1.getAccount_balance());
        assertEquals(3500, account2.getAccount_balance());


    }






}
