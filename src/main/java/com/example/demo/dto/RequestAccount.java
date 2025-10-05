package com.example.demo.dto;

import com.example.demo.entity.Account;
import com.example.demo.entity.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class RequestAccount {

    private Long account_id;
    private String account_number;
    private double account_balance;
    private Account.accountType type;
    private Long userid;
    private Set<Transaction> transactionid;


}
