package com.example.demo.controller;

import com.example.demo.dto.RequestMonthly;
import com.example.demo.entity.MonthlyStatement;
import com.example.demo.service.MonthlyStatementService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("/monthly")
public class MonthlyStatmentController {


    private final MonthlyStatementService monthlyStatementService;


    public MonthlyStatmentController(MonthlyStatementService monthlyStatementService) {
        this.monthlyStatementService = monthlyStatementService;

    }



    @PostMapping("/generate/{month}/{upToDate}")
    public MonthlyStatement generateStatementupdate(@RequestBody RequestMonthly request, @PathVariable String month, @PathVariable String upToDate) {
        LocalDate upTo = LocalDate.parse(upToDate);  //convert from string for localdate ..for use as date  deal with transactions and db
        return monthlyStatementService.generateStatement(request, month, upTo);
    }


    //بيجيب كل اكونت الكشف الحساب بتاع الشهر والسنه بتاعه
    @GetMapping("/get")
    public MonthlyStatement getMonthStatement(@RequestBody RequestMonthly request) {

        return monthlyStatementService.getMonthStatement(request);

    }


}
