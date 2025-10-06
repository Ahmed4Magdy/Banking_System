package com.example.demo.controller;


import com.example.demo.dto.RequestAccount;
import com.example.demo.entity.Account;
import com.example.demo.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acount")
public class AccountController {


    @Autowired
    private AccountServiceImpl accountServiceimpl;


    @PostMapping("/create")
    public ResponseEntity<?> CreatAccount(@RequestBody RequestAccount request) {
        Account saveaccount = accountServiceimpl.CreatAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveaccount);

    }

    @GetMapping("/{account_id}")
    public Account getAccountDetailsWithAccountNumber(@PathVariable Long account_id) {
        return accountServiceimpl.getAccountDetailsWithAccountNumber(account_id);

    }

    @GetMapping("")
    public List<Account> getAllAccountDetails() {

        return accountServiceimpl.getAllAccountDetails();
    }

    @GetMapping("/byuser/{userId}")
    public List<Account> getAccountsByUser(@PathVariable Long userId) {
        return accountServiceimpl.getAccountsByUser(userId);
    }


    @PutMapping("/modfiyaccount/{account_id}")
    public Account updateAccount(@PathVariable Long account_id, @RequestBody Account account) {

        return accountServiceimpl.updateAccount(account_id, account);

    }


    @DeleteMapping("/{account_id}")
    public void CloseAccount(@PathVariable Long account_id) {

        accountServiceimpl.CloseAccount(account_id);

    }


}
