package com.example.demo.controller;

import com.example.demo.dto.TransactionDto;
import com.example.demo.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionServiceInterface;

    @PostMapping("/deposit")
    public TransactionDto Deposit(@RequestBody @Valid TransactionDto request) {

        return transactionServiceInterface.Deposit(request);

    }

    @PostMapping("/withdraw")
    public TransactionDto Withdraw(@RequestBody @Valid TransactionDto request) {

        return transactionServiceInterface.Withdraw(request);

    }

    @PostMapping("/transfer")
    public TransactionDto Transfer(@RequestBody @Valid TransactionDto request) {

        return transactionServiceInterface.Transfer(request);


    }


    @DeleteMapping("/{id}")
    public void DeleteTarnsaction(@PathVariable Long id){
        transactionServiceInterface.DeleteTarnsaction(id);
    }


    @GetMapping("/account/{accountId}")
    public List<TransactionDto> getTransactionsByAccount(@PathVariable Long accountId) {
        return transactionServiceInterface.getTransactionsByAccount(accountId);
    }



}
