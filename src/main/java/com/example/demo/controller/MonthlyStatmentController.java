package com.example.demo.controller;

import com.example.demo.dto.MonthlyStatementDto;
import com.example.demo.service.MonthlyService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("/monthly")
public class MonthlyStatmentController {


    private final MonthlyService monthlyService;


    public MonthlyStatmentController(MonthlyService monthlyService) {
        this.monthlyService = monthlyService;

    }


    @PostMapping("/generate/{month}/{upToDate}")
    public MonthlyStatementDto generateStatementupdate(@RequestBody MonthlyStatementDto request, @PathVariable String month, @PathVariable String upToDate) {
        LocalDate upTo = LocalDate.parse(upToDate);  //convert from string for localdate ..for use as date  deal with transactions and db
        return monthlyService.generateStatement(request, month, upTo);
    }


    //بيجيب كل اكونت الكشف الحساب بتاع الشهر والسنه بتاعه
    @GetMapping("/{accountId}/{month}")
    public MonthlyStatementDto getfindByAccountIdAndMonth(@PathVariable Long accountId, @PathVariable String month) {
        return monthlyService.getfindByAccountIdAndMonth(accountId, month);
    }


    @DeleteMapping("/{id}")
    public void Delete(@PathVariable Long id) {
        monthlyService.delete(id);
    }


}
