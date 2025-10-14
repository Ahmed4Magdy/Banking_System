package com.example.demo.service;

import com.example.demo.dto.MonthlyStatementDto;

import java.time.LocalDate;

public interface MonthlyService {


    MonthlyStatementDto generateStatement(MonthlyStatementDto request, String month, LocalDate upToDate);

    MonthlyStatementDto getfindByAccountIdAndMonth(Long accountId, String month);


}
