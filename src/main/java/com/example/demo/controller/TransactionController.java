package com.example.demo.controller;

import com.example.demo.dto.RequestTransaction;
import com.example.demo.entity.Account;
import com.example.demo.entity.Transaction;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/deposit")
    public Transaction Deposit(@RequestBody RequestTransaction request) {

        return transactionService.Deposit(request);

    }

    @PostMapping("/withdraw")
    public Transaction Withdraw(@RequestBody RequestTransaction request) {

        return transactionService.Withdraw(request);

    }

    @PostMapping("/transfer")
    public Transaction Transfer(@RequestBody RequestTransaction request) {

        return transactionService.Transfer(request);


    }


    @DeleteMapping("/{id}")
    public void DeleteTarnsactio(@PathVariable Long id){
        transactionService.DeleteTarnsactio(id);
    }

}
