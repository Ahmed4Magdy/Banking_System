package com.example.demo.service;

import com.example.demo.dto.TransactionDto;
import com.example.demo.entity.Account;
import com.example.demo.entity.Transaction;
import com.example.demo.mapper.TransactionMapper;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {


    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionMapper transactionMapper;

    // 1    1000
    public TransactionDto Deposit(TransactionDto dto) {

        Account account = accountRepository.findById(dto.getAccountId())
                .orElseThrow(() -> new RuntimeException("not found account with " + dto.getAccountId()));

        account.setAccount_balance(account.getAccount_balance() + dto.getAmount());
        accountRepository.save(account);   // لان اي حسابات لازم تتعمل يدويا من دي تي اوو ل انتيتي

        Transaction transaction = transactionMapper.toEntity(dto);
        transaction.setAccount(account);
        transaction.setDate(LocalDateTime.now());

        transaction = transactionRepository.save(transaction);
        return transactionMapper.toDTO(transaction);


    }


    public TransactionDto Withdraw(TransactionDto dto) {

        Account account = accountRepository.findById(dto.getAccountId()).orElseThrow(() -> new RuntimeException("not found account with " + dto.getAccountId()));
        if (account.getAccount_balance() < dto.getAmount()) {
            throw new RuntimeException("Insufficient balance");
        }
        account.setAccount_balance(account.getAccount_balance() - dto.getAmount());
        accountRepository.save(account);

        Transaction transaction = transactionMapper.toEntity(dto);
        transaction.setAccount(account);
        transaction.setDate(LocalDateTime.now());

        transaction = transactionRepository.save(transaction);
        return transactionMapper.toDTO(transaction);

    }


    public TransactionDto Transfer(TransactionDto dto) {

        Account source = accountRepository.findById(dto.getAccountId()).orElseThrow(() -> new RuntimeException("not found account"));

        Account target = accountRepository.findById(dto.getTargetAccountId()).orElseThrow(() -> new RuntimeException("not found account"));

        if (source.getAccount_balance() < dto.getAmount()) {
            throw new RuntimeException("Insufficient balance");
        }

        source.setAccount_balance(source.getAccount_balance() - dto.getAmount());
        target.setAccount_balance(target.getAccount_balance() + dto.getAmount());

        accountRepository.save(source);
        accountRepository.save(target);

        Transaction transaction = transactionMapper.toEntity(dto);
        transaction.setAccount(source);
        transaction.setDate(LocalDateTime.now());

        transaction = transactionRepository.save(transaction);

        TransactionDto resultDto = transactionMapper.toDTO(transaction);
        return resultDto;

    }


    public void DeleteTarnsaction(Long id) {
        transactionRepository.deleteById(id);
    }


    public List<TransactionDto> getTransactionsByAccount(Long accountId) {
        return transactionRepository.findByAccountId(accountId)
                .stream()
                .map(transactionMapper::toDTO).toList();
    }


}
