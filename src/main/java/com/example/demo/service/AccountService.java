package com.example.demo.service;

import com.example.demo.dto.AccountDto;

import java.util.List;

public interface AccountService {


    AccountDto CreatAccount(AccountDto dto);

    AccountDto getAccountDetailsWithAccountNumber(Long account_id);

    List<AccountDto> getAllAccountDetails();


    AccountDto updateAccount(Long account_id, AccountDto dto);

    void CloseAccount(Long account_id);

    List<AccountDto> getAccountsByUser(Long userId);

}
