package com.example.demo.service.impl;

import com.example.demo.dto.AccountDto;
import com.example.demo.entity.Account;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public AccountDto CreatAccount(AccountDto dto) {

        Account account = accountMapper.toEntity(dto);

        account.setUser(userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"))
        );
//        account.setCreatedAt(LocalDate.now()); // ✅ يوم إنشاء الحساب
        accountRepository.save(account);
        AccountDto accountDto = accountMapper.toDto(account);
        return accountDto;

    }

    @Override
    public AccountDto getAccountDetailsWithAccountNumber(Long account_id) {

        Account account = accountRepository.findById(account_id).orElseThrow(() -> new RuntimeException("Not found account with " + account_id));
        return accountMapper.toDto(account);
    }

    @Override
    public List<AccountDto> getAllAccountDetails() {

        return accountRepository.findAll().stream()
                .map(accountMapper::toDto)
                .toList();
    }


    @Override
    public AccountDto updateAccount(Long account_id, AccountDto dto) {

        Account account1 = accountRepository.findById(account_id).orElseThrow(() -> new RuntimeException("account not found wiht " + account_id));
        accountMapper.updateAccountFromDto(dto, account1);  // المفروض ان ال متسجل دي تي اوو ف اما هاجي اعمل ابديت هعدل ال ف الانتيتي ك دي تي او واحفظه ف ال داتا بيز
        accountRepository.save(account1);

        AccountDto dto1 = accountMapper.toDto(account1);
        return dto1;
    }

    @Override
    public void CloseAccount(Long account_id) {

        accountRepository.deleteById(account_id);


    }

    @Override
    public List<AccountDto> getAccountsByUser(Long userId) {
        return accountRepository.findByUserId(userId)
                .stream()
                .map(accountMapper::toDto)
                .toList();
    }


}
