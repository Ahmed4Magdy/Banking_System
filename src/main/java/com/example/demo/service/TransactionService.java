package com.example.demo.service;

import com.example.demo.dto.RequestTransaction;
import com.example.demo.entity.Account;
import com.example.demo.entity.Transaction;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class TransactionService {


    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;

    // 1    1000
    public Transaction Deposit(RequestTransaction request) {

        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new RuntimeException("not found account with " + request.getAccountId()));

        account.setAccount_balance(account.getAccount_balance() + request.getAmount());
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setType(Transaction.Type.DEPOSIT);
        transaction.setAmount(request.getAmount());
        transaction.setDate(LocalDateTime.now());


        return transactionRepository.save(transaction);

    }


    public Transaction Withdraw(RequestTransaction request) {

        Account account = accountRepository.findById(request.getAccountId()).orElseThrow(() -> new RuntimeException("not found account with " + request.getAccountId()));
        if (account.getAccount_balance() < request.getAmount()) {
            throw new RuntimeException("Insufficient balance");
        }
        account.setAccount_balance(account.getAccount_balance() - request.getAmount());
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setType(Transaction.Type.WITHDRAW);
        transaction.setAmount(request.getAmount());
        transaction.setDate(request.getDate());

        return transactionRepository.save(transaction);

    }


    public Transaction Transfer(RequestTransaction request) {

        Account source = accountRepository.findById(request.getAccountId()).orElseThrow(() -> new RuntimeException("not found account"));

        Account target = accountRepository.findById(request.getTargetAccountId()).orElseThrow(() -> new RuntimeException("not found account"));

        if (source.getAccount_balance() < request.getAmount()) {
            throw new RuntimeException("Insufficient balance");
        }

        source.setAccount_balance(source.getAccount_balance() - request.getAmount());
        target.setAccount_balance(target.getAccount_balance() + request.getAmount());

        accountRepository.save(source);
        accountRepository.save(target);

        Transaction transaction = new Transaction();
        transaction.setType(Transaction.Type.TRANSFER);
        transaction.setAmount(request.getAmount());
        transaction.setDate(LocalDateTime.now());
        transaction.setAccount(source);

        return transactionRepository.save(transaction);
    }


    public void DeleteTarnsactio(Long id) {
        transactionRepository.deleteById(id);
    }


}
