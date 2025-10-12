package com.example.demo.services;

import com.example.demo.dto.MonthlyStatementDto;
import com.example.demo.entity.Account;
import com.example.demo.entity.MonthlyStatement;
import com.example.demo.mapper.MonthlyMapper;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.MonthlyStatementRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.MonthlyStatementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MonthlyStatementServiceTest {

    @Mock
    private MonthlyStatementRepository monthlyStatementRepository;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private MonthlyMapper monthlyMapper;
    @InjectMocks
    private MonthlyStatementService monthlyStatementService;


    @Test
    void generateMonthlyStatement() {


        MonthlyStatementDto dto = new MonthlyStatementDto();
        dto.setAccountId(1L);

        Account account = new Account(1L, "ac12", 2000.0, Account.accountType.SAVINGS, LocalDate.now(), null, null, null);

        MonthlyStatement entity = new MonthlyStatement();
        entity.setId(100L);


        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(transactionRepository.findByAccountAndDateBetween(any(), any(), any())).thenReturn(List.of());
        when(monthlyStatementRepository.save(any(MonthlyStatement.class))).thenReturn(entity);
        when(monthlyMapper.toDTO(entity)).thenReturn(dto);
        MonthlyStatementDto result = monthlyStatementService.generateStatement(dto, "2025-10", LocalDate.now());

        assertNotNull(result);
        assertEquals(dto, result);
        verify(monthlyStatementRepository).save(any());


    }

}
