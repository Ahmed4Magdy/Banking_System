package com.example.demo.service;

import com.example.demo.dto.RequestAccount;
import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;

    public Account CreatAccount(RequestAccount request) {

        Account account = new Account();
        account.setAccount_number(request.getAccount_number());
        account.setAccount_balance(request.getAccount_balance());
        account.setType(request.getType());
        User user = userRepository.findById(request.getUserid()).orElseThrow(() -> new RuntimeException("not found id with " + request.getUserid()));
        account.setUser(user);
        return accountRepository.save(account);

    }


    public Account getAccountDetailsWithAccountNumber(Long account_id) {

        Account account = accountRepository.findById(account_id).orElseThrow(() -> new RuntimeException("Not found account with " + account_id));
        return account;

    }

    public List<Account> getAllAccountDetails() {

        return accountRepository.findAll();
    }




    public Account updateAccount(Long account_id, Account account) {

        Account account1 = accountRepository.findById(account_id).orElseThrow(() -> new RuntimeException("account not found wiht " + account_id));
        account1.setAccount_number(account.getAccount_number());
        account1.setAccount_balance(account.getAccount_balance());
        return accountRepository.save(account1);

    }


    public void CloseAccount(Long account_id) {

        accountRepository.deleteById(account_id);


    }


    public List<Account> getAccountsByUser(Long userId) {
        return accountRepository.findByUserId(userId);
    }



}
