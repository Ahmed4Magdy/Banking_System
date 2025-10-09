package com.example.demo.service;

import com.example.demo.dto.AccountDto;
import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.UserRepository;
import lombok.experimental.PackagePrivate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AccountServiceImpl {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountMapper accountMapper;

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


    public AccountDto getAccountDetailsWithAccountNumber(Long account_id) {

        Account account = accountRepository.findById(account_id).orElseThrow(() -> new RuntimeException("Not found account with " + account_id));
        return accountMapper.toDto(account);
    }

    public List<AccountDto> getAllAccountDetails() {

        return accountRepository.findAll().stream()
                .map(accountMapper::toDto)
                .toList();
    }



    public AccountDto updateAccount(Long account_id, AccountDto dto) {

        Account account1 = accountRepository.findById(account_id).orElseThrow(() -> new RuntimeException("account not found wiht " + account_id));
        accountMapper.updateAccountFromDto(dto,account1);  // المفروض ان ال متسجل دي تي اوو ف اما هاجي اعمل ابديت هعدل ال ف الانتيتي ك دي تي او واحفظه ف ال داتا بيز
        accountRepository.save(account1);

        AccountDto dto1 =accountMapper.toDto(account1);
        return dto1;
    }


    public void CloseAccount(Long account_id) {

        accountRepository.deleteById(account_id);


    }


    public List<AccountDto> getAccountsByUser(Long userId) {
        return accountRepository.findByUserId(userId)
                .stream()
                .map(accountMapper::toDto)
                .toList();
    }



}
