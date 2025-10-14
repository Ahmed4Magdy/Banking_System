package com.example.demo.controller;


import com.example.demo.dto.AccountDto;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {


    @Autowired
    private AccountService accountService;


    @PostMapping("/create")
    public ResponseEntity<?> CreatAccount(@RequestBody AccountDto dto) {
        AccountDto saveaccount = accountService.CreatAccount(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveaccount);

    }

    @GetMapping("/{account_id}")
    public AccountDto getAccountDetailsWithAccountNumber(@PathVariable Long account_id) {
        return accountService.getAccountDetailsWithAccountNumber(account_id);

    }

    @GetMapping("")
    public List<AccountDto> getAllAccountDetails() {

        return accountService.getAllAccountDetails();
    }

    @GetMapping("/byuser/{userId}")
    public List<AccountDto> getAccountsByUser(@PathVariable Long userId) {
        return accountService.getAccountsByUser(userId);
    }


    @PutMapping("/modfiyaccount/{account_id}")
    public AccountDto updateAccount(@PathVariable Long account_id, @RequestBody AccountDto dto) {

        return accountService.updateAccount(account_id, dto);

    }


    @DeleteMapping("/{account_id}")
    public void CloseAccount(@PathVariable Long account_id) {

        accountService.CloseAccount(account_id);

    }


}
