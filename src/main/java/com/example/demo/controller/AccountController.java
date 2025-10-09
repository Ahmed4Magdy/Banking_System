package com.example.demo.controller;


import com.example.demo.dto.AccountDto;
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
    public ResponseEntity<?> CreatAccount(@RequestBody AccountDto dto) {
        AccountDto saveaccount = accountServiceimpl.CreatAccount(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveaccount);

    }

    @GetMapping("/{account_id}")
    public AccountDto getAccountDetailsWithAccountNumber(@PathVariable Long account_id) {
        return accountServiceimpl.getAccountDetailsWithAccountNumber(account_id);

    }

    @GetMapping("")
    public List<AccountDto> getAllAccountDetails() {

        return accountServiceimpl.getAllAccountDetails();
    }

    @GetMapping("/byuser/{userId}")
    public List<AccountDto> getAccountsByUser(@PathVariable Long userId) {
        return accountServiceimpl.getAccountsByUser(userId);
    }


    @PutMapping("/modfiyaccount/{account_id}")
    public AccountDto updateAccount(@PathVariable Long account_id, @RequestBody AccountDto dto) {

        return accountServiceimpl.updateAccount(account_id, dto);

    }


    @DeleteMapping("/{account_id}")
    public void CloseAccount(@PathVariable Long account_id) {

        accountServiceimpl.CloseAccount(account_id);

    }


}
