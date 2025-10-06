package com.example.demo.service;

import com.example.demo.dto.RequestAccount;
import com.example.demo.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {


    Account CreatAccount(RequestAccount request);

    Account getAccountDetailsWithAccountNumber(Long account_id);

    List<Account> getAllAccountDetails();


    Account updateAccount(Long account_id, Account account);

    void CloseAccount(Long account_id);

    List<Account> getAccountsByUser(Long userId);

}
