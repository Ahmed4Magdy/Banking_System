package com.example.demo.controller;

import com.example.demo.dto.TransactionDto;
import com.example.demo.entity.Transaction;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/deposit")
    public TransactionDto Deposit(@RequestBody TransactionDto request) {

        return transactionService.Deposit(request);

    }

    @PostMapping("/withdraw")
    public TransactionDto Withdraw(@RequestBody TransactionDto request) {

        return transactionService.Withdraw(request);

    }

    @PostMapping("/transfer")
    public TransactionDto Transfer(@RequestBody TransactionDto request) {

        return transactionService.Transfer(request);


    }


    @DeleteMapping("/{id}")
    public void DeleteTarnsaction(@PathVariable Long id){
        transactionService.DeleteTarnsaction(id);
    }


    @GetMapping("/account/{accountId}")
    public List<TransactionDto> getTransactionsByAccount(@PathVariable Long accountId) {
        return transactionService.getTransactionsByAccount(accountId);
    }



}
