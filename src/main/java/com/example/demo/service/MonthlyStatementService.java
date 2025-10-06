package com.example.demo.service;


import com.example.demo.dto.RequestMonthly;
import com.example.demo.entity.Account;
import com.example.demo.entity.MonthlyStatement;
import com.example.demo.entity.Transaction;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.MonthlyStatementRepository;
import com.example.demo.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class MonthlyStatementService {


    private final MonthlyStatementRepository monthlyStatementRepository;

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    public MonthlyStatementService(MonthlyStatementRepository monthlyStatementRepository, AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.monthlyStatementRepository = monthlyStatementRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }


    public MonthlyStatement generateStatement(RequestMonthly request, String month, LocalDate upToDate) {
        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        YearMonth ym = YearMonth.parse(month); // ex: "2025-10"
        LocalDate start = ym.atDay(1);

        // لو الشهر لسه ماكملش، ناخد لحد اليوم الحالي أو لحد upToDate اللي تبعته
        LocalDate end = (upToDate != null && upToDate.isBefore(ym.atEndOfMonth()))
                ? upToDate
                : ym.atEndOfMonth();

        List<Transaction> monthlyTransactions =
                transactionRepository.findByAccountAndDateBetween(account, start.atStartOfDay(), end.atTime(23, 59));

        double openingsbalance = account.getAccount_balance();
        for (Transaction t : monthlyTransactions) {
            if (t.getType() == Transaction.Type.DEPOSIT) {
                openingsbalance -= t.getAmount();

            } else if (t.getType() == Transaction.Type.WITHDRAW || t.getType() == Transaction.Type.TRANSFER) {
                openingsbalance += t.getAmount();
            }
        }


        double sumdeposit = 0.0;
        for (Transaction t : monthlyTransactions) {
            if (t.getType() == Transaction.Type.DEPOSIT) {
                sumdeposit += t.getAmount();
            }
        }

        double sumwithdraw = 0.0;
        for (Transaction t : monthlyTransactions) {
            if (t.getType() == Transaction.Type.WITHDRAW) {
                sumwithdraw += t.getAmount();
            }
        }

        double sumtransfer = 0.0;
        for (Transaction t : monthlyTransactions) {
            if (t.getType() == Transaction.Type.TRANSFER) {
                sumtransfer += t.getAmount();
            }
        }


        double interest = 0.0;
        if (account.getType().equals("SAVINGS") && end.equals(ym.atEndOfMonth())) {
            interest = openingsbalance * 0.02;

        }


        MonthlyStatement statement = new MonthlyStatement();
        statement.setAccount(account);
        statement.setOpeningBalance(openingsbalance);
        statement.setClosingBalance(account.getAccount_balance() + interest);
        statement.setMonth(month);
        statement.setInterestAdded(interest);
        statement.setSumdeposit(sumdeposit);
        statement.setSumwithdraw(sumwithdraw);
        statement.setSumtransfer(sumtransfer);

        return monthlyStatementRepository.save(statement);


    }


    //بيجيب كل اكونت الكشف الحساب بتاع الشهر والسنه بتاعه
    public MonthlyStatement getMonthStatement(RequestMonthly request) {

        return monthlyStatementRepository.findByAccountIdAndMonth(request.getAccountId(), request.getMonth());

    }
}
